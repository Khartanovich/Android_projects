package com.example.skillcinema.domain.home.usecase

import com.example.skillcinema.domain.home.HomeMainScreenRepository
import com.example.skillcinema.entity.home.MovieEntity
import javax.inject.Inject

class GetTopMovieUseCase @Inject constructor(private val repository: HomeMainScreenRepository) {
    suspend fun getTopFilms(page: Int): List<MovieEntity>{
        return repository.getTopFilms(page)
    }
}