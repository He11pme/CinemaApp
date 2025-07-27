package skillfactory.education.cinemaapp.carousel

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginLeft
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import skillfactory.education.cinemaapp.Film
import skillfactory.education.cinemaapp.R

class Carousel(val carousel: RecyclerView) {

    val snapHelper = StartLinearSnapHelper()
    val filmList = mutableListOf<Film>()

    init {
        carousel.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = CarouselAdapter(filmList)

        }

        snapHelper.attachToRecyclerView(carousel)
    }

    val layoutManager = carousel.layoutManager as LinearLayoutManager


    fun createCarousel() {
        //Надо подключить сюда IMDB API
        filmList.addAll(
            listOf(
                Film("Oppenheimer", 8.3, R.drawable.film_one),
                Film("Spider-Man", 8.2, R.drawable.film_two),
                Film("Venom", 6.6, R.drawable.film_three),
                Film("Wall-E", 8.4, R.drawable.film_four),
                Film("Hellboy", 5.3, R.drawable.film_five),
                Film("I don't know", 10.0, R.drawable.film_six),
                Film("A Working Man", 5.7, R.drawable.film_seven),
                Film("Kraken", 5.5, R.drawable.film_eight),
            )
        )
    }

    fun startScrollPoster() {
        val itemCount = carousel.layoutManager?.itemCount ?: return

        CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                delay(10000)
                val currentPosition = getCurrentPosition()
                val isLastCompletelyVisible = layoutManager.findLastCompletelyVisibleItemPosition() == itemCount - 1
                val nextPosition = when {
                    isLastCompletelyVisible -> 0
                    currentPosition + 1 in 0 until itemCount -> currentPosition + 1
                    else -> 0
                }
                smoothScrollToPositionForce(nextPosition)
            }
        }
    }

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

    private fun getCurrentPosition(): Int {
        val snapView = snapHelper.findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
        return carousel.getChildAdapterPosition(snapView)
    }
}