package com.example.skillcinema.data.home.dto

import com.example.skillcinema.entity.home.SimilarFilmEntity
import com.google.gson.annotations.SerializedName

data class SimilarFilmDto(
    @SerializedName("filmId")
    override val filmId: Int,
    @SerializedName("nameRu")
    override val nameRu: String,
    @SerializedName("nameEn")
    override val nameEn: String,
    @SerializedName("posterUrl")
    override val posterUrl: String,
    @SerializedName("posterUrlPreview")
    override val posterUrlPreview: String
) : SimilarFilmEntity