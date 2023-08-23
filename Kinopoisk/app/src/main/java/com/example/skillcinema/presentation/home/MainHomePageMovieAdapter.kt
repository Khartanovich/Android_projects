package com.example.skillcinema.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skillcinema.databinding.ItemButtonShowAllBinding
import com.example.skillcinema.databinding.ItemMovieHomePageBinding
import com.example.skillcinema.entity.home.MovieEntity


class MainHomeMovieAdapter(
    private val onItemClick: OnItemClick,
    private val visibleButtonShowAll: Boolean,
    private val nameSelection: String?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data: List<MovieEntity> = emptyList()
    fun setData(data: List<MovieEntity>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == FILMS_HOLDER) {
            MovieViewHolder(
                ItemMovieHomePageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            ButtonShowAllViewHolder(
                ItemButtonShowAllBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data.getOrNull(position)
        when (holder.itemViewType) {
            FILMS_HOLDER -> {
                (holder as MovieViewHolder).bind(item)
                holder.itemView.setOnClickListener {
                    item?.let {
                        onItemClick.onClick(it)
                    }
                }
            }
            BUTTON_SHOW_ALL_HOLDER -> {
                holder.itemView.setOnClickListener {
                    item?.let {
                        if (nameSelection != null) {
                            onItemClick.onClickShowAll(nameSelection)
                        }
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == data.lastIndex && visibleButtonShowAll) {
            BUTTON_SHOW_ALL_HOLDER
        } else FILMS_HOLDER
    }


    companion object {
        const val FILMS_HOLDER = 1
        const val BUTTON_SHOW_ALL_HOLDER = 2
    }
}

class MovieViewHolder(val binding: ItemMovieHomePageBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MovieEntity?) {
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

class ButtonShowAllViewHolder(val binding: ItemButtonShowAllBinding) :
    RecyclerView.ViewHolder(binding.root)

interface OnItemClick {
    fun onClick(movie: MovieEntity)
    fun onClickShowAll(nameSelection: String)
}



