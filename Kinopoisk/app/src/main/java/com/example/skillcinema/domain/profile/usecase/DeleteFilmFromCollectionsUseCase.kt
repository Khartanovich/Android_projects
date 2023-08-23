package com.example.skillcinema.domain.profile.usecase

import com.example.skillcinema.domain.profile.ProfileMainScreenRepository
import javax.inject.Inject

class DeleteFilmFromCollectionsUseCase @Inject constructor(private val repository: ProfileMainScreenRepository) {
    fun deleteFilmFromCollections(collectionName: String){
        repository.deleteFilmFromCollections(collectionName)
    }
}