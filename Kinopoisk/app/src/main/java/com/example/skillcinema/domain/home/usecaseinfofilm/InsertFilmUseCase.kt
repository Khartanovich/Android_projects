package com.example.skillcinema.domain.home.usecaseinfofilm

import com.example.skillcinema.domain.home.InfoAboutFilmScreenRepository
import com.example.skillcinema.entity.profile.FilmEntity
import javax.inject.Inject

class InsertFilmUseCase @Inject constructor(private  val repository: InfoAboutFilmScreenRepository) {
    suspend fun insertFilm(film: FilmEntity){
        repository.insertFilm(film)
    }
}