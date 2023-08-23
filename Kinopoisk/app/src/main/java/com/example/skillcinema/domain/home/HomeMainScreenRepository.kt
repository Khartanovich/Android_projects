package com.example.skillcinema.domain.home

import com.example.skillcinema.entity.home.MovieEntity

interface HomeMainScreenRepository {
    suspend fun getPremieresFilms(year: Int, month: String): List<MovieEntity>

    suspend fun getTopFilms(page: Int): List<MovieEntity>

    suspend fun getPopularFilms(page: Int): List<MovieEntity>

    suspend fun getRandomFilms(): List<MovieEntity>
}