package com.example.skillcinema.domain.home.usecaseinfofilm

import com.example.skillcinema.domain.home.InfoAboutFilmScreenRepository
import com.example.skillcinema.entity.profile.FilmsAndCollectionsCrossRef
import javax.inject.Inject

class DeleteCrossReferenceUseCase @Inject constructor(private val repository: InfoAboutFilmScreenRepository) {
    fun deleteCrossReference(crossReference: FilmsAndCollectionsCrossRef){
        repository.deleteCrossReference(crossReference)
    }
}