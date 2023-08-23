package com.example.skillcinema.domain.home.usecaseinfofilm

import com.example.skillcinema.domain.home.InfoAboutFilmScreenRepository
import com.example.skillcinema.entity.profile.FilmsAndCollectionsCrossRef
import javax.inject.Inject

class InsertFilmsAndCollectionCrossRefUseCase @Inject constructor(private val repository: InfoAboutFilmScreenRepository) {
    suspend fun insertFilmsAndCollectionCrossRef(crossReference: FilmsAndCollectionsCrossRef){
        repository.insertFilmsAndCollectionCrossRef(crossReference)
    }
}