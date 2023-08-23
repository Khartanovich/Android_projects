package com.example.skillcinema.data.home.dto


import com.example.skillcinema.entity.home.ImagesGalleryEntity
import com.google.gson.annotations.SerializedName

data class ImagesGalleryDto(
    @SerializedName("items")
    override val items: List<ItemGalleryDto>,
    @SerializedName("total")
    override val total: Int,
    @SerializedName("totalPages")
    override val totalPages: Int
) : ImagesGalleryEntity