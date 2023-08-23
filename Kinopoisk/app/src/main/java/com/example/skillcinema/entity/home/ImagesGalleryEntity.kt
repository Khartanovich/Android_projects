package com.example.skillcinema.entity.home


interface ImagesGalleryEntity {
    val items: List<ItemGalleryEntity>
    val total: Int
    val totalPages: Int
}
interface ItemGalleryEntity{
    val imageUrl: String?
    val previewUrl: String?
}