package com.example.skillcinema.domain.home.usecase

import com.example.skillcinema.domain.home.HomeMainScreenRepository
import com.example.skillcinema.entity.home.MovieEntity
import javax.inject.Inject

class GetPremiersMovieUseCase @Inject constructor(private val repository: HomeMainScreenRepository) {
    suspend fun getPremieresFilms(year: Int, month: String): List<MovieEntity> {
        return repository.getPremieresFilms(year, month)
    }
}