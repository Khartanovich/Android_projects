package com.example.skillcinema.presentation.home.filmography

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skillcinema.databinding.ItemMovieFilmographyBinding
import com.example.skillcinema.entity.home.staff.FilmStaffEntity

class FilmographyAdapter(
    private val onFilmClick : (FilmStaffEntity) -> Unit
) : RecyclerView.Adapter<FilmographyViewHolder>() {

    private var movies: List<FilmStaffEntity> = emptyList()
    fun setFilm(films: List<FilmStaffEntity>) {
        this.movies = films
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmographyViewHolder {
        return FilmographyViewHolder(
            ItemMovieFilmographyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FilmographyViewHolder, position: Int) {
        val film = movies.getOrNull(position)
        with(holder.binding) {
            Glide.with(poster.context).load(
                "$URL_IMAGES${film?.filmId}.jpg"
            )
                .into(poster)
            nameRu.text = film?.nameRu ?: film?.nameEn
            rating.text = film?.rating
            description.text = film?.description
        }
        holder.binding.root.setOnClickListener {
            film?.let { onFilmClick(it) }
        }
    }

    override fun getItemCount(): Int = movies.size

    companion object{
        private const val URL_IMAGES = "https://kinopoiskapiunofficial.tech/images/posters/kp_small/"
    }
}

class FilmographyViewHolder(
    val binding: ItemMovieFilmographyBinding
) : RecyclerView.ViewHolder(binding.root)