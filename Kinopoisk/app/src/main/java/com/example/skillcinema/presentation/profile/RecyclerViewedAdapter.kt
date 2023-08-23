package com.example.skillcinema.presentation.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skillcinema.databinding.ItemMovieHomePageBinding
import com.example.skillcinema.entity.profile.FilmEntity

class RecyclerViewedAdapter(
    private val onClickFilmItem: (FilmEntity) -> Unit
) : RecyclerView.Adapter<RecyclerViewedViewHolder>() {

    private var films: List<FilmEntity> = emptyList()
    fun setFilms(films: List<FilmEntity>) {
        this.films = films
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewedViewHolder {
        return RecyclerViewedViewHolder(
            ItemMovieHomePageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewedViewHolder, position: Int) {
        val film = films.getOrNull(position)
        with(holder.binding) {
            title.text = film?.nameRu
            genres.text = film?.genres
            film?.let {
                Glide
                    .with(poster.context)
                    .load(it.posterUrlPreview)
                    .into(poster)
            }
        }
        holder.binding.root.setOnClickListener {
            film?.let {
                onClickFilmItem(it)
            }
        }

    }

    override fun getItemCount(): Int = films.size
}

class RecyclerViewedViewHolder(val binding: ItemMovieHomePageBinding) :
    RecyclerView.ViewHolder(binding.root)