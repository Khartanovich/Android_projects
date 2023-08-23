package com.example.skillcinema.presentation.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skillcinema.databinding.ItemForCollectionFragmentBinding
import com.example.skillcinema.entity.profile.FilmEntity

class CollectionAdapter(private val onFilmClick: (FilmEntity) -> Unit) :
    RecyclerView.Adapter<CollectionViewHolder>() {

    private var films: List<FilmEntity> = emptyList()
    fun getFilms(film: List<FilmEntity>) {
        this.films = film
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        return CollectionViewHolder(
            ItemForCollectionFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        val filmInCollection = films.getOrNull(position)
        with(holder.binding) {
            title.text = filmInCollection?.nameRu
            genres.text = filmInCollection?.genres
            rating.text = filmInCollection?.ratingImdb.toString()
            filmInCollection?.let {
                Glide.with(posterUrl.context).load(it.posterUrlPreview).into(posterUrl)
            }
        }
        holder.binding.root.setOnClickListener {
            filmInCollection?.let {
                onFilmClick(it)
            }
        }
    }

    override fun getItemCount(): Int = films.size
}

class CollectionViewHolder(val binding: ItemForCollectionFragmentBinding) :
    RecyclerView.ViewHolder(binding.root)