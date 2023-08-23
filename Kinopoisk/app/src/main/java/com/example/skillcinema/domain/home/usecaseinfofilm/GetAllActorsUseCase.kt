package com.example.skillcinema.domain.home.usecaseinfofilm

import com.example.skillcinema.data.home.dto.AllActorsModelDto
import com.example.skillcinema.domain.home.InfoAboutFilmScreenRepository
import com.example.skillcinema.entity.home.AllActorsEntity
import javax.inject.Inject

class GetAllActorsUseCase @Inject constructor(private val repository: InfoAboutFilmScreenRepository) {
    suspend fun getAllActors(id: Int): List<AllActorsEntity>{
        return repository.getAllActors(id)
    }
}