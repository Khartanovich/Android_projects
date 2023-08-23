package com.example.skillcinema.data.home.dto


import com.example.skillcinema.entity.home.ItemGalleryEntity
import com.google.gson.annotations.SerializedName

data class ItemGalleryDto(
    @SerializedName("imageUrl")
    override val imageUrl: String?,
    @SerializedName("previewUrl")
    override val previewUrl: String?
) : ItemGalleryEntity