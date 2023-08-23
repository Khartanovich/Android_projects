package com.example.skillcinema.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skillcinema.data.profile.FilmDao
import com.example.skillcinema.domain.profile.usecase.GetAllCollectionsDataBaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val getAllCollectionsDataBaseUseCase: GetAllCollectionsDataBaseUseCase
) : ViewModel() {
    val filmsInCollection =
        this.getAllCollectionsDataBaseUseCase.getAllCollectionsDataBase().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )
}