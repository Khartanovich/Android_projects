package com.example.skillcinema.presentation.home.allActors

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skillcinema.domain.home.usecaseinfofilm.GetAllActorsUseCase
import com.example.skillcinema.entity.home.AllActorsEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllActorsViewModel @Inject constructor(private val getAllActorsUseCase: GetAllActorsUseCase): ViewModel() {

    private val _allActors = MutableStateFlow<List<AllActorsEntity>>(emptyList())
    val allActors = _allActors.asStateFlow()

    fun getAllActors(filmId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                getAllActorsUseCase.getAllActors(filmId)
            }.fold(
                onSuccess = { _allActors.value = it },
                onFailure = { Log.d("MyLog", it.message ?: "") }
            )
        }
    }

}