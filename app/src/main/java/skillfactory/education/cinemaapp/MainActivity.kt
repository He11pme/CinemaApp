package skillfactory.education.cinemaapp

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginTop
import androidx.core.view.setPadding
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import skillfactory.education.cinemaapp.databinding.ActivityMainBinding
import skillfactory.education.cinemaapp.carousel.Carousel
import skillfactory.education.cinemaapp.omdbapi.ListMovies

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.bottomAppBar) { view, insets ->
            view.setPadding(view.paddingLeft, view.paddingTop, view.paddingRight, 0) // Убираем нижний отступ
            insets
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.toolbar) { view, insets ->
            val topInset = insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            view.setPadding(view.paddingLeft, topInset, view.paddingRight, view.paddingBottom)
            insets
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            binding.bottomNavigationView.background = null
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            @Suppress("DEPRECATION")
            WindowCompat.setDecorFitsSystemWindows(window, false)
            window.statusBarColor = Color.TRANSPARENT
        }

        lifecycleScope.launch {
            Carousel(binding.rvCarousel).apply {
                createCarousel(ListMovies.todayInCinemaIdList)
                startScrollPoster()
            }


        }

    }


    fun showToastForMenu(item: MenuItem) {
        Toast.makeText(this, "${item.title}", Toast.LENGTH_SHORT).show()
    }

    fun buttonToast() {
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

}