package com.example.jetpackccompose.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackccompose.data.model.character.CharacterDto
import com.example.jetpackccompose.domain.GetAllCharactersUseCase
import com.example.jetpackccompose.domain.GetEpisodeUseCase
import com.example.jetpackccompose.domain.GetInfoAboutAllLocationsUseCase
import com.example.jetpackccompose.domain.GetInfoAboutOneCharacterUseCase
import com.example.jetpackccompose.entity.CharacterEntity
import com.example.jetpackccompose.entity.EpisodeEntity
import com.example.jetpackccompose.entity.LocationEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val getInfoAboutAllLocationsUseCase: GetInfoAboutAllLocationsUseCase,
    private val getInfoAboutOneCharacterUseCase: GetInfoAboutOneCharacterUseCase,
    private val getEpisodeUseCase: GetEpisodeUseCase
) : ViewModel() {

    private var _oneCharacter = MutableStateFlow<CharacterDto?>(null)
    val oneCharacter = _oneCharacter.asStateFlow()

    private var _episode = MutableStateFlow<List<EpisodeEntity>>(emptyList())
    val episode = _episode.asStateFlow()

    suspend fun loadAllCharacters(page: Int): List<CharacterEntity> {
        return getAllCharactersUseCase.getAllCharacters(page)
    }

    suspend fun loadAllLocations(page: Int): List<LocationEntity> {
        return getInfoAboutAllLocationsUseCase.getAllLocations(page)
    }

    fun getInfoAboutOneCharacter(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                getInfoAboutOneCharacterUseCase.getOneCharacter(id)
            }.fold(
                onSuccess = { _oneCharacter.value = it as CharacterDto },
                onFailure = { Log.d("MyLog", it.message ?: "") }
            )
        }
    }

    fun getEpisode(listId: List<Int>){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                delay(1000)
                getEpisodeUseCase.getEpisode(listId)
            }.fold(
                onSuccess = { _episode.value = it},
                onFailure = { Log.d("MyLog", it.message ?: "") }
            )
        }
    }
}