package com.example.skillcinema.data.home.dto

import com.google.gson.annotations.SerializedName

data class MovieListDto(
    @SerializedName("total")
    val total: Int,
    @SerializedName("items")
    val items: List<MovieDto>
)
