package com.example.skillcinema.domain.home.usecaseinfofilm

import com.example.skillcinema.domain.home.InfoAboutFilmScreenRepository
import com.example.skillcinema.entity.home.InformationAboutFilmEntity
import javax.inject.Inject

class GetInformationUseCase @Inject constructor(private val repository: InfoAboutFilmScreenRepository) {
    suspend fun getInformation(id: Int): InformationAboutFilmEntity?{
        return repository.getInformation(id)
    }
}