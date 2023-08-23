package com.example.skillcinema.domain.home.usecaseinfofilm

import com.example.skillcinema.domain.home.InfoAboutFilmScreenRepository
import com.example.skillcinema.entity.home.SimilarFilmsEntity
import javax.inject.Inject

class GetSimilarFilmsUseCase @Inject constructor(private val repository: InfoAboutFilmScreenRepository) {
    suspend fun getSimilarFilms(id: Int): SimilarFilmsEntity{
        return repository.getSimilarFilms(id)
    }
}