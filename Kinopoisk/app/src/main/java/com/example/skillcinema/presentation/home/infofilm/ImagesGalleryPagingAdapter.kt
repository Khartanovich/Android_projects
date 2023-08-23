package com.example.skillcinema.presentation.home.infofilm

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skillcinema.databinding.ItemImageGalleryBinding
import com.example.skillcinema.entity.home.ItemGalleryEntity

class ImagesGalleryPagingAdapter:
    PagingDataAdapter<ItemGalleryEntity, ImagesGalleryViewHolder>(DiffUtilCallback()) {

    override fun onBindViewHolder(holder: ImagesGalleryViewHolder, position: Int) {
        val imageItem = getItem(position)
        Glide.with(holder.binding.previewUrl.context)
            .load(imageItem?.previewUrl)
            .into(holder.binding.previewUrl)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesGalleryViewHolder {
        val binding = ItemImageGalleryBinding.inflate(LayoutInflater.from(parent.context))
        return ImagesGalleryViewHolder(binding)
    }
}

class ImagesGalleryViewHolder(
    val binding: ItemImageGalleryBinding
) : RecyclerView.ViewHolder(binding.root)

class DiffUtilCallback : DiffUtil.ItemCallback<ItemGalleryEntity>() {
    override fun areItemsTheSame(oldItem: ItemGalleryEntity, newItem: ItemGalleryEntity): Boolean {
        return oldItem.previewUrl == newItem.previewUrl
    }

    override fun areContentsTheSame(oldItem: ItemGalleryEntity, newItem: ItemGalleryEntity): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }
}