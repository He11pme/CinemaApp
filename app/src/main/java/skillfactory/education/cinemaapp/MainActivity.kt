package skillfactory.education.cinemaapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.UncontainedCarouselStrategy
import kotlinx.coroutines.Runnable
import skillfactory.education.cinemaapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            //Неправильно отображается bottomAppBar.
            //Пришлось сделать отрицательный отступ на высоту navBarHeight
            val navBarHeight = insets.getInsets(WindowInsetsCompat.Type.navigationBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, -navBarHeight.bottom)
            insets
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.searchView) { v, insets ->
            // Полностью отключил отступы для searchView
            v.setPadding(0, 0, 0, 0)
            WindowInsetsCompat.CONSUMED
        }

        createCarousel()

//        scrollPosters(binding.rvCarousel, snapHelper)

    }

    fun createCarousel() {
        val imageList = mutableListOf(
            R.drawable.film_one, R.drawable.film_two, R.drawable.film_three,
            R.drawable.film_four, R.drawable.film_five, R.drawable.film_six,
            R.drawable.film_seven, R.drawable.film_eight
        )

        binding.rvCarousel.apply {
            setHasFixedSize(true)
            layoutManager = CarouselLayoutManager(UncontainedCarouselStrategy())
            adapter = CarouselAdapter(imageList)
        }

        val snapHelper = CarouselSnapHelper().also {
            it.attachToRecyclerView(binding.rvCarousel)
        }
    }

    fun autoScrollPosters(recyclerView: RecyclerView) {

    }

//    fun scrollPosters(recyclerView: RecyclerView, snapHelper: CarouselSnapHelper) {
//        val handler = Handler(Looper.getMainLooper())
//
//        val scrollRunnable = object : Runnable {
//            override fun run() {
//                val itemCount = recyclerView.layoutManager?.itemCount ?: return
//                val currentPosition = getCurrentPosition(recyclerView, snapHelper)
//                val nextPosition =
//                    if (currentPosition == RecyclerView.NO_POSITION || currentPosition + 1 >= itemCount) 0 else currentPosition + 1
//                recyclerView.smoothScrollToPosition(nextPosition)
//                handler.postDelayed(this, 10000)
//            }
//
//        }
//        handler.post(scrollRunnable)
//    }
//
//    fun getCurrentPosition(recyclerView: RecyclerView, snapHelper: CarouselSnapHelper): Int {
//        val layoutManager =
//            recyclerView.layoutManager as? CarouselLayoutManager ?: return RecyclerView.NO_POSITION
//        val snapView = snapHelper.findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
//        return recyclerView.getChildAdapterPosition(snapView)
//    }
}