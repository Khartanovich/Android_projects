package com.example.skillcinema.data.home

import com.example.skillcinema.domain.home.HomeMainScreenRepository
import com.example.skillcinema.entity.home.MovieEntity
import com.example.skillcinema.ui.home.RetrofitServices
import javax.inject.Inject

class HomeMainScreenRepositoryImpl @Inject constructor(): HomeMainScreenRepository {

    override suspend fun getPremieresFilms(year: Int, month: String): List<MovieEntity> {
        return RetrofitServices.retrofitService.getPremiersMovies(year,month).items
    }

    override suspend fun getTopFilms(page: Int): List<MovieEntity> {
        return RetrofitServices.retrofitService.getTopMovies(page).films
    }

    override suspend fun getPopularFilms(page: Int): List<MovieEntity> {
        return RetrofitServices.retrofitService.getPopularsMovies(page).films
    }

    override suspend fun getRandomFilms(): List<MovieEntity> {
        TODO("Not yet implemented")
    }
}