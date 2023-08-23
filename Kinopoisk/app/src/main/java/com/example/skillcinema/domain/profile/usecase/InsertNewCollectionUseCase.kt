package com.example.skillcinema.domain.profile.usecase

import com.example.skillcinema.domain.profile.ProfileMainScreenRepository
import com.example.skillcinema.entity.profile.Collections
import javax.inject.Inject

class InsertNewCollectionUseCase @Inject constructor(private val repository: ProfileMainScreenRepository) {
    suspend fun insertNewCollection(collections: Collections){
        repository.insertNewCollection(collections)
    }
}