package com.example.skillcinema.domain.search

import com.example.skillcinema.entity.search.SearchFilmEntity

interface SearchScreenRepository {
    suspend fun searchFilms(
        page: Int,
        keyword: String?,
        country: Int,
        genres: Int,
        ratingFrom: Int,
        ratingTo: Int,
        yearFrom: Int,
        yearTo: Int,
        order: String,
        type: String
    ): SearchFilmEntity
}