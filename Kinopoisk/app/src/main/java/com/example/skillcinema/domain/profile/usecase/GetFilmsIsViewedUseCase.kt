package com.example.skillcinema.domain.profile.usecase

import com.example.skillcinema.domain.profile.ProfileMainScreenRepository
import com.example.skillcinema.entity.profile.FilmEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFilmsIsViewedUseCase @Inject constructor(private val repository: ProfileMainScreenRepository) {
    fun getFilmsIsViewed(): Flow<List<FilmEntity>> {
        return repository.getFilmsIsViewed()
    }
}