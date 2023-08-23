package com.example.skillcinema.domain.home.usecaseinfofilm

import com.example.skillcinema.domain.home.InfoAboutFilmScreenRepository
import com.example.skillcinema.entity.profile.FilmsAndCollectionsCrossRef
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCrossReferenceUseCase @Inject constructor(private val repository: InfoAboutFilmScreenRepository) {
    fun getAllCrossReference(): Flow<List<FilmsAndCollectionsCrossRef>>{
        return repository.getAllCrossReference()
    }
}