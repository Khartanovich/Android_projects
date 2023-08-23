package com.example.skillcinema.domain.profile.usecase

import com.example.skillcinema.domain.profile.ProfileMainScreenRepository
import javax.inject.Inject

class DeleteCollectionFromDataBaseUseCase @Inject constructor(private val repository: ProfileMainScreenRepository) {
    fun deleteCollectionFromDataBase(collectionName: String){
        repository.deleteCollectionFromDataBase(collectionName)
    }
}