package com.example.skillcinema.presentation.search

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skillcinema.databinding.ItemMovieHomePageBinding
import com.example.skillcinema.entity.home.MovieEntity

class SearchPagingAdapter(
    private val onClickMovie: (MovieEntity) -> Unit
) : PagingDataAdapter<MovieEntity, SearchPagingAdapter.SearchViewHolder>(DiffUtilCallback()) {

    class SearchViewHolder(val binding: ItemMovieHomePageBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: MovieEntity?){
            binding.title.text = item?.nameRu ?: ""
            binding.genres.text = item?.genres?.joinToString(", ") { it.genre }
            item?.let {
                Glide
                    .with(binding.poster.context)
                    .load(it.posterUrlPreview)
                    .into(binding.poster)
            }
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<MovieEntity>() {
        override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem.kinopoiskId == newItem.kinopoiskId
        }
        override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem.kinopoiskId == newItem.kinopoiskId
        }
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val film = getItem(position)
        holder.bind(film)
        holder.binding.root.setOnClickListener {
            film?.let {
                onClickMovie(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemMovieHomePageBinding.inflate(LayoutInflater.from(parent.context))
        return SearchViewHolder(binding)
    }
}