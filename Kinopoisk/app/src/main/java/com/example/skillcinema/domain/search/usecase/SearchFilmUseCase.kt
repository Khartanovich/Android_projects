package com.example.skillcinema.domain.search.usecase

import com.example.skillcinema.data.search.SearchFilmsDto
import com.example.skillcinema.domain.search.SearchScreenRepository
import com.example.skillcinema.entity.search.SearchFilmEntity
import javax.inject.Inject

class SearchFilmUseCase @Inject constructor(private val repository: SearchScreenRepository) {
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
    ): SearchFilmEntity {
        return repository.searchFilms(
            page,
            keyword,
            country,
            genres,
            ratingFrom,
            ratingTo,
            yearFrom,
            yearTo,
            order,
            type
        )
    }
}