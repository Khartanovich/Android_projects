package com.example.skillcinema.data.search

import com.example.skillcinema.data.home.dto.MovieDto
import com.example.skillcinema.entity.search.SearchFilmEntity
import com.google.gson.annotations.SerializedName

data class SearchFilmsDto(
    @SerializedName("total")
    override val total: Int,
    @SerializedName("totalPages")
    override val totalPages: Int,
    @SerializedName("items")
    override val items: List<MovieDto>
) : SearchFilmEntity
