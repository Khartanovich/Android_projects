package com.example.jetpackccompose.domain

import com.example.jetpackccompose.entity.CharacterEntity
import com.example.jetpackccompose.entity.EpisodeEntity
import com.example.jetpackccompose.entity.LocationEntity

interface MyRepository {
     suspend fun getAllCharacters(page: Int): List<CharacterEntity>
     suspend fun getAllLocations(page: Int): List<LocationEntity>
     suspend fun getOneCharacter(id: Int): CharacterEntity
     suspend fun getEpisode(listId: List<Int>): List<EpisodeEntity>
}