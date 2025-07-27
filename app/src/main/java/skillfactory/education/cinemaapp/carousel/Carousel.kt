package skillfactory.education.cinemaapp.carousel

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import skillfactory.education.cinemaapp.Movie
import skillfactory.education.cinemaapp.omdbapi.OmdbRepository
import skillfactory.education.cinemaapp.omdbapi.ReaderMovie

class Carousel(val carousel: RecyclerView) {

    val snapHelper = StartLinearSnapHelper()
    val filmList = mutableListOf<Movie>()
    val adapter = CarouselAdapter(filmList)

    init {
        carousel.setHasFixedSize(true)
        carousel.layoutManager = LinearLayoutManager(carousel.context, LinearLayoutManager.HORIZONTAL, false)
        carousel.adapter = adapter

        snapHelper.attachToRecyclerView(carousel)
    }

    val layoutManager = carousel.layoutManager as LinearLayoutManager
    suspend fun createCarousel(listIdMovie: List<String>) {

        val repository = OmdbRepository()
        val readerMovie = ReaderMovie()
        listIdMovie.forEach { id ->
            repository.fetchAndSaveMovieJson(carousel.context, id, "3920f6e0")

            withContext(Dispatchers.Main) {
                readerMovie.readMovieFromFile(carousel.context, id)?.let { movie ->
                    filmList.add(movie)
                    adapter.notifyItemInserted(filmList.lastIndex)
                }
            }
        }
    }

    fun startScrollPoster() {
        val itemCount = carousel.layoutManager?.itemCount ?: return

        CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                delay(10000)
                val currentPosition = getCurrentPosition()
                val isLastCompletelyVisible =
                    layoutManager.findLastCompletelyVisibleItemPosition() == itemCount - 1
                val nextPosition = when {
                    isLastCompletelyVisible -> 0
                    currentPosition + 1 in 0 until itemCount -> currentPosition + 1
                    else -> 0
                }
                smoothScrollToPositionForce(nextPosition)
            }
        }
    }

    private fun getCurrentPosition(): Int {
        val snapView = snapHelper.findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
        return carousel.getChildAdapterPosition(snapView)
    }

    /**
     * Использует кастомный LinearSmoothScroller для плавной прокрутики списка
     * к указанной позиции, с приоритетом фиксации элемента в начале списка
     * @param position Позиция элемента, к которому нужно прокрутить
     */
    private fun smoothScrollToPositionForce(position: Int) {

        val smoothScroller =
            object : LinearSmoothScroller(carousel.context) {
                override fun getHorizontalSnapPreference(): Int {
                    return SNAP_TO_START
                }
            }
        smoothScroller.targetPosition = position
        layoutManager.startSmoothScroll(smoothScroller)

    }

}