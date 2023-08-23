package com.example.skillcinema.domain.home.usecaseinfofilm

import com.example.skillcinema.domain.home.InfoAboutFilmScreenRepository
import com.example.skillcinema.entity.profile.FilmsAndCollections
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFilmsDataBaseUseCase @Inject constructor(private val repository: InfoAboutFilmScreenRepository) {
    fun getAllFilmsDataBase(): Flow<List<FilmsAndCollections>>{
        return repository.getAllFilmsDataBase()
    }
}