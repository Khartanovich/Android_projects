package com.example.skillcinema.data.search

import com.example.skillcinema.domain.search.SearchScreenRepository
import com.example.skillcinema.entity.search.SearchFilmEntity
import com.example.skillcinema.ui.home.RetrofitServices
import javax.inject.Inject

class SearchScreenRepositoryImpl @Inject constructor(): SearchScreenRepository {
    override suspend fun searchFilms(
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
        return RetrofitServices.retrofitService.searchFilm(
            page,
            keyword,
            country,
            genres,
            ratingFrom,
            ratingTo,
            yearFrom,
            yearTo,
            order, type
        )
    }
}