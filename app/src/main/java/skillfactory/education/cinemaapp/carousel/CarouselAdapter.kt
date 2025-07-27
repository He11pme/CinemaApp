package skillfactory.education.cinemaapp.carousel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import skillfactory.education.cinemaapp.Film
import skillfactory.education.cinemaapp.databinding.PosterCarouselBinding

class CarouselAdapter(private val filmList: List<Film>) :
    RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {
    inner class CarouselViewHolder(private val binding: PosterCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(film: Film) {
            Picasso.get()
                .load(film.poster)
                .centerCrop()
                .fit()
                .into(binding.poster)

            binding.tvName.text = film.name
            binding.tvRating.text = film.rating.toString()
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
        holder.bind(filmList[position])
    }

    override fun getItemCount(): Int {
        return filmList.size
    }

}