package com.example.skillcinema.domain.home.usecase

import com.example.skillcinema.domain.home.HomeMainScreenRepository
import com.example.skillcinema.entity.home.MovieEntity
import javax.inject.Inject

class GetPopularMovieUseCase @Inject constructor(private val repository: HomeMainScreenRepository) {
    suspend fun getPopularFilms(page: Int): List<MovieEntity>{
        return  repository.getPopularFilms(page)
    }
}