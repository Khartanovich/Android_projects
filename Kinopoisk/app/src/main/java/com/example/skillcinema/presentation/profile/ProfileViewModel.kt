package com.example.skillcinema.presentation.profile

import androidx.lifecycle.*
import com.example.skillcinema.entity.profile.Collections
import com.example.skillcinema.data.profile.FilmDao
import com.example.skillcinema.domain.profile.ProfileMainScreenRepository
import com.example.skillcinema.domain.profile.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getFilmsIsViewedCase: GetFilmsIsViewedUseCase,
    private val insertNewCollectionUseCase: InsertNewCollectionUseCase,
    private val getAllCollectionsDataBaseUseCase: GetAllCollectionsDataBaseUseCase,
    private val deleteCollectionFromDataBaseUseCase: DeleteCollectionFromDataBaseUseCase,
    private val deleteFilmFromCollectionsUseCase: DeleteFilmFromCollectionsUseCase
) : ViewModel() {

    val getFilmsIsViewed = this.getFilmsIsViewedCase.getFilmsIsViewed().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    fun createNewCollection(collectionName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            insertNewCollectionUseCase.insertNewCollection(Collections(collectionName))
        }
    }

    val getCollectionsAndFilms =
        this.getAllCollectionsDataBaseUseCase.getAllCollectionsDataBase().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    fun deleteCollection(collectionName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteCollectionFromDataBaseUseCase.deleteCollectionFromDataBase(collectionName)
            deleteFilmFromCollectionsUseCase.deleteFilmFromCollections(collectionName)
        }
    }
}

