package skillfactory.education.cinemaapp

import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.UncontainedCarouselStrategy
import kotlinx.coroutines.Runnable

class Carousel(val carousel: RecyclerView) {
    val snapHelper = CarouselSnapHelper()
    fun createCarousel() {
        //Надо подключить сюда IMDB API
        val filmList = listOf(
            Film("Oppenheimer", 8.3, R.drawable.film_one),
            Film("Spider-Man", 8.2, R.drawable.film_two),
            Film("Venom", 6.6, R.drawable.film_three),
            Film("Wall-E", 8.4, R.drawable.film_four),
            Film("Hellboy", 5.3, R.drawable.film_five),
            Film("I don't know", 10.0, R.drawable.film_six),
            Film("A Working Man", 5.7, R.drawable.film_seven),
            Film("Kraken", 5.5, R.drawable.film_eight),
        )

        carousel.apply {
            setHasFixedSize(true)
            layoutManager = CarouselLayoutManager(UncontainedCarouselStrategy())
            adapter = CarouselAdapter(filmList)

        }

        snapHelper.attachToRecyclerView(carousel)

    }
    //Не совсем правильная реализация функции.
    //Когда последний элемент полность влезает в экран, но при этом активным остается предыдущий
    //Карусель не двигается
    fun startScrollPoster() {
        val handler = Handler(Looper.getMainLooper())

        val scrollRunnable = object : Runnable {
            override fun run() {
                handler.postDelayed(this, 10000)
                val itemCount = carousel.layoutManager?.itemCount ?: return
                val currentPosition = getCurrentPosition()
                val nextPosition =
                    if (currentPosition == RecyclerView.NO_POSITION || currentPosition + 1 >= itemCount) 0 else currentPosition + 1
                carousel.smoothScrollToPosition(nextPosition)
            }

        }
        handler.post(scrollRunnable)
    }
    private fun getCurrentPosition(): Int {
        val layoutManager = carousel.layoutManager
        val snapView = snapHelper.findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
        return carousel.getChildAdapterPosition(snapView)
    }
}