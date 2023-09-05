package com.example.jetpackccompose.data

import com.example.jetpackccompose.domain.MyRepository
import com.example.jetpackccompose.entity.CharacterEntity
import com.example.jetpackccompose.entity.EpisodeEntity
import com.example.jetpackccompose.entity.LocationEntity

class MyRepositoryImpl: MyRepository {
    override suspend fun getAllCharacters(page: Int): List<CharacterEntity>{
        return RetrofitServices.rickAndMortyCharacter.getAllCharacterApi(page).results
    }

    override suspend fun getAllLocations(page: Int): List<LocationEntity>{
        return RetrofitServices.rickAndMortyCharacter.getAllLocationsApi(page).infoLocations
    }

    override suspend fun getOneCharacter(id: Int): CharacterEntity {
        return RetrofitServices.rickAndMortyCharacter.getOneCharacter(id)
    }

    override suspend fun getEpisode(listId: List<Int>): List<EpisodeEntity> {
        return RetrofitServices.rickAndMortyCharacter.getEpisode(listId)
    }
}