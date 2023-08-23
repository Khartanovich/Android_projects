package com.example.skillcinema.domain.profile.usecase

import com.example.skillcinema.domain.profile.ProfileMainScreenRepository
import com.example.skillcinema.entity.profile.CollectionsAndFilms
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCollectionsDataBaseUseCase @Inject constructor(private val repository: ProfileMainScreenRepository) {
    fun getAllCollectionsDataBase(): Flow<List<CollectionsAndFilms>>{
        return repository.getAllCollectionsDataBase()
    }
}