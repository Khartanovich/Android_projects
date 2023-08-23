package com.example.skillcinema.presentation.home.allmoviesinselection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.skillcinema.databinding.ItemMovieHomePageBinding
import com.example.skillcinema.entity.home.MovieEntity
import com.example.skillcinema.presentation.home.MovieViewHolder

class SelectionsFilmsPagingAdapter(
    private val onPagingItemClick: (MovieEntity) -> Unit
) : PagingDataAdapter<MovieEntity, MovieViewHolder>(
    DiffUtilCallback()
) {
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val itemMovie = getItem(position)
        with(holder) {
            binding.title.text = itemMovie?.nameRu
            binding.genres.text = itemMovie?.genres?.joinToString(", ") { it.genre }
            itemMovie?.let {
                Glide
                    .with(binding.poster.context)
                    .load(it.posterUrlPreview)
                    .into(binding.poster)
            }
        }
        holder.binding.root.setOnClickListener {
            itemMovie?.let {
                onPagingItemClick(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieHomePageBinding.inflate(LayoutInflater.from(parent.context))
        return MovieViewHolder(binding)
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<MovieEntity>() {
    override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem.kinopoiskId == newItem.kinopoiskId
    }

    override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem.nameRu == newItem.nameRu
    }
}