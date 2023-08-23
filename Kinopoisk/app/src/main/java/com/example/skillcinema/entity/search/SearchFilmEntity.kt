package com.example.skillcinema.entity.search

import com.example.skillcinema.data.home.dto.MovieDto
import com.example.skillcinema.entity.home.MovieEntity
import com.google.gson.annotations.SerializedName

interface SearchFilmEntity {
    val total: Int
    val totalPages: Int
    val items: List<MovieEntity>
}