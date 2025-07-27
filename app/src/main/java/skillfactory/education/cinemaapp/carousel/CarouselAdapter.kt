package skillfactory.education.cinemaapp.carousel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import skillfactory.education.cinemaapp.Movie
import skillfactory.education.cinemaapp.databinding.PosterCarouselBinding

class CarouselAdapter(private val movieList: List<Movie>) :
    RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {
    inner class CarouselViewHolder(private val binding: PosterCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            Picasso.get()
                .load(movie.poster)
                .centerCrop()
                .fit()
                .into(binding.ivPoster)

            binding.tvTitle.text = movie.title
            binding.tvImdbRating.text = movie.imdbRating
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarouselViewHolder {
        return CarouselViewHolder(
            PosterCarouselBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: CarouselViewHolder,
        position: Int
    ) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

}