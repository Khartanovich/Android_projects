package com.example.jetpackccompose.domain

import com.example.jetpackccompose.entity.CharacterEntity
import javax.inject.Inject

class GetInfoAboutOneCharacterUseCase @Inject constructor(private val repository: MyRepository) {
    suspend fun getOneCharacter(id: Int): CharacterEntity {
        return repository.getOneCharacter(id)
    }
}