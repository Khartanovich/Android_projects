package com.example.skillcinema.domain.home.usecase

import com.example.skillcinema.domain.home.HomeMainScreenRepository
import com.example.skillcinema.entity.home.MovieEntity
import javax.inject.Inject

class GetRandomMovieUseCase @Inject constructor(private val repository: HomeMainScreenRepository) {
    suspend fun getRandomFilms(): List<MovieEntity>{
        return repository.getRandomFilms()
    }
}