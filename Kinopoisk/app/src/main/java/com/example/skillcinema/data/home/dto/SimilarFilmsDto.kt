package com.example.skillcinema.data.home.dto

import com.example.skillcinema.entity.home.SimilarFilmsEntity
import com.google.gson.annotations.SerializedName

data class SimilarFilmsDto(
    @SerializedName("total")
    override val total: Int,
    @SerializedName("items")
    override val items: List<SimilarFilmDto>
) : SimilarFilmsEntity
