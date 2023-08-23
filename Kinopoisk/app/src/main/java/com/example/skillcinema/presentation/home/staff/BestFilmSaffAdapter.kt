package com.example.skillcinema.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skillcinema.databinding.ItemMovieHomePageBinding
import com.example.skillcinema.data.home.dto.InformationAboutFilmDto


class BestFilmStaffAdapter(
    private val onItemClickBestFilm: OnItemClickBestFilm,
) : RecyclerView.Adapter<BestFilmViewHolder>() {
    private var data: List<InformationAboutFilmDto> = emptyList()
    fun setData(data: List<InformationAboutFilmDto>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestFilmViewHolder {
        return BestFilmViewHolder(
            ItemMovieHomePageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BestFilmViewHolder, position: Int) {
        val item = data.getOrNull(position)
        with(holder.binding) {
            if (item == data.last() && data.size > 10) {
                title.visibility = View.GONE
                genres.visibility = View.GONE
                goToAllPremiers.visibility = View.VISIBLE
                poster.visibility = View.GONE
                rating.visibility = View.GONE
//слушатель нажатия на кнопку для показа всех фильмов, если в ответе придет болеше 20 фильмов
            } else {
                goToAllPremiers.visibility = View.GONE
                title.text = item?.nameRu ?: ""
                genres.text = item?.genres?.joinToString(", ") { it?.genre.toString() }
                rating.text = item?.ratingKinopoisk.toString()
                item?.let {
                    Glide
                        .with(poster.context)
                        .load(it.posterUrlPreview)
                        .into(poster)
                }
            }
        }
//слушатель нажатия на элемент списка - на фильм
        holder.binding.root.setOnClickListener {
            item?.let {
                onItemClickBestFilm.onClick(it)
            }
        }
    }
}

class BestFilmViewHolder(val binding: ItemMovieHomePageBinding) : RecyclerView.ViewHolder(binding.root)

interface OnItemClickBestFilm {
    fun onClick(movie: InformationAboutFilmDto)
}
