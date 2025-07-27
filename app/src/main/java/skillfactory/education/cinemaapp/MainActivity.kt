package skillfactory.education.cinemaapp

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import skillfactory.education.cinemaapp.databinding.ActivityMainBinding
import skillfactory.education.cinemaapp.carousel.Carousel

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

        Carousel(binding.rvCarousel).apply {
            createCarousel()
            startScrollPoster()
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            binding.bottomNavigationView.background = null
        }

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_bookmarks -> {
                    showToastForMenu(item)
                    true
                }

                R.id.ic_dice -> {
                    showToastForMenu(item)
                    true
                }

                R.id.ic_journal -> {
                    showToastForMenu(item)
                    true
                }

                R.id.ic_ticket -> {
                    showToastForMenu(item)
                    true
                }

                else -> false
            }
        }

        binding.floatingActionButton.setOnClickListener {
            Toast.makeText(this, "Главная", Toast.LENGTH_SHORT).show()
        }

        binding.toolbar.setNavigationOnClickListener {
            Toast.makeText(this, "Меню", Toast.LENGTH_SHORT).show()
        }

        binding.toolbar.setOnMenuItemClickListener {
            showToastForMenu(it)
            true
        }


    }

    fun showToastForMenu(item: MenuItem) {
        Toast.makeText(this, "${item.title}", Toast.LENGTH_SHORT).show()
    }

}