package com.example.jetpackccompose.domain

import com.example.jetpackccompose.entity.CharacterEntity
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(private val repository: MyRepository) {
    suspend fun getAllCharacters(page: Int): List<CharacterEntity> {
        return repository.getAllCharacters(page)
    }
}