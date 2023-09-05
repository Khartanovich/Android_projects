package com.example.jetpackccompose.domain

import com.example.jetpackccompose.entity.LocationEntity
import javax.inject.Inject

class GetInfoAboutAllLocationsUseCase @Inject constructor(private val repository: MyRepository) {
    suspend fun getAllLocations(page: Int): List<LocationEntity> {
        return repository.getAllLocations(page)
    }
}