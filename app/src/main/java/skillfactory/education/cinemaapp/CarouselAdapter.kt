package skillfactory.education.cinemaapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import skillfactory.education.cinemaapp.databinding.PosterCarouselBinding

class CarouselAdapter(private val imageList: MutableList<Int>) : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    inner class CarouselViewHolder(private val binding: PosterCarouselBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int) {
            Picasso.get()
                .load(image)
                .centerCrop()
                .fit()
                .into(binding.poster)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarouselViewHolder {
        return CarouselViewHolder(PosterCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: CarouselViewHolder,
        position: Int
    ) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

}