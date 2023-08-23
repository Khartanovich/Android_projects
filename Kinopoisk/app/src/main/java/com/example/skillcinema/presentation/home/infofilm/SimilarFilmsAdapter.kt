package com.example.skillcinema.presentation.home.infofilm

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skillcinema.databinding.ItemSimilarFilmBinding
import com.example.skillcinema.entity.home.SimilarFilmEntity

class SimilarFilmsAdapter(
    private val onClickSimilarFilm: (SimilarFilmEntity) -> Unit
) : RecyclerView.Adapter<SimilarFilmsViewHolder>() {

    private var similarFilms: List<SimilarFilmEntity> = emptyList()
    fun setSimilarFilm(data: List<SimilarFilmEntity>) {
        this.similarFilms = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarFilmsViewHolder {
        return SimilarFilmsViewHolder(
            ItemSimilarFilmBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SimilarFilmsViewHolder, position: Int) {
        val similarFilm = similarFilms.getOrNull(position)
        with(holder.binding) {
            Glide.with(posterSimilar.context).load(similarFilm?.posterUrlPreview)
                .into(posterSimilar)
            nameRuSimilar.text = similarFilm?.nameRu
            nameEnSimilar.text = similarFilm?.nameEn
        }
        holder.binding.root.setOnClickListener {
            let {
                similarFilm?.let {
                    onClickSimilarFilm(it)
                }
            }
        }
    }

    override fun getItemCount(): Int = similarFilms.size
}

class SimilarFilmsViewHolder(val binding: ItemSimilarFilmBinding) :
    RecyclerView.ViewHolder(binding.root)