package skillfactory.education.cinemaapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
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
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageList = mutableListOf<Int>()
        imageList.add(R.drawable.film_one)
        imageList.add(R.drawable.film_two)
        imageList.add(R.drawable.film_three)
        imageList.add(R.drawable.film_four)
        imageList.add(R.drawable.film_five)
        imageList.add(R.drawable.film_six)
        imageList.add(R.drawable.film_seven)
        imageList.add(R.drawable.film_nine)

        binding.rvCarousel.apply {
            setHasFixedSize(true)
            layoutManager = CarouselLayoutManager()
            adapter = CarouselAdapter(imageList)
        }

        CarouselSnapHelper().attachToRecyclerView(binding.rvCarousel)

    }

}