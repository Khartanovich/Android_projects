package com.example.skillcinema.presentation.home.infofilm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.skillcinema.entity.profile.Collections
import com.example.skillcinema.domain.home.usecaseinfofilm.*
import com.example.skillcinema.domain.profile.usecase.GetAllCollectionsDataBaseUseCase
import com.example.skillcinema.domain.profile.usecase.GetFilmsIsViewedUseCase
import com.example.skillcinema.domain.profile.usecase.InsertNewCollectionUseCase
import com.example.skillcinema.entity.home.AllActorsEntity
import com.example.skillcinema.entity.home.InformationAboutFilmEntity
import com.example.skillcinema.entity.home.ItemGalleryEntity
import com.example.skillcinema.entity.home.SimilarFilmsEntity
import com.example.skillcinema.entity.profile.FilmEntity
import com.example.skillcinema.entity.profile.FilmsAndCollectionsCrossRef
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InformationAboutFilmViewModel @Inject constructor(
    private val deleteCrossReferenceUseCase: DeleteCrossReferenceUseCase,
    private val deleteFilmFromDataBaseUseCase: DeleteFilmFromDataBaseUseCase,
    private val getAllActorsUseCase: GetAllActorsUseCase,
    private val getAllCrossReferenceUseCase: GetAllCrossReferenceUseCase,
    private val getAllFilmsDataBaseUseCase: GetAllFilmsDataBaseUseCase,
    private val getImagesFilmUseCase: GetImagesFilmUseCase,
    private val getInformationUseCase: GetInformationUseCase,
    private val getSimilarFilmsUseCase: GetSimilarFilmsUseCase,
    private val insertFilmsAndCollectionCrossRefUseCase: InsertFilmsAndCollectionCrossRefUseCase,
    private val insertFilmUseCase: InsertFilmUseCase,
    private val updateIsViewedUseCase: UpdateIsViewedUseCase,
    private val insertNewCollectionUseCase: InsertNewCollectionUseCase,
    private val getAllCollectionsDataBaseUseCase: GetAllCollectionsDataBaseUseCase,
    private val getFilmsIsViewedUseCase: GetFilmsIsViewedUseCase
) : ViewModel() {

    private val _information = MutableStateFlow<InformationAboutFilmEntity?>(null)
    val information = _information.asStateFlow()

    private val _allActors = MutableStateFlow<List<AllActorsEntity>>(emptyList())
    val allActors = _allActors.asStateFlow()

    private val _similarFilms = MutableStateFlow<SimilarFilmsEntity?>(null)
    val similarFilms = _similarFilms.asStateFlow()

    fun getInformation(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                getInformationUseCase.getInformation(id)
            }.fold(
                onSuccess = { _information.value = it },
                onFailure = { Log.d("MyLog", it.message ?: "") }
            )
        }
    }

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

    fun getImagesGallery(id: Int, type: String): Flow<PagingData<ItemGalleryEntity>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            ImagesGalleryPagingSource(getImagesFilmUseCase, id, type)
        }
    ).flow.cachedIn(viewModelScope)

    fun getSimilarFilms(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                getSimilarFilmsUseCase.getSimilarFilms(id)
            }.fold(
                onSuccess = {
                    _similarFilms.value = it
                },
                onFailure = { Log.d("MyLog", it.message ?: "") }
            )
        }
    }

    fun insertFilmToDataBase(film: FilmEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            insertFilmUseCase.insertFilm(film)
        }
    }

    fun updateFilmIsViewed(film: FilmEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            updateIsViewedUseCase.updateIsViewed(film.kinopoiskId, film.isViewed!!)
        }
    }

    fun deleteFilmFromDataBase(kinopoiskId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFilmFromDataBaseUseCase.deleteFilmFromDataBase(kinopoiskId)
        }
    }

    val getAllFilmsDataBase = this.getAllFilmsDataBaseUseCase.getAllFilmsDataBase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    fun insertNewCollection(nameCollection: String) {
        viewModelScope.launch(Dispatchers.IO) {
            insertNewCollectionUseCase.insertNewCollection(Collections(nameCollection))
        }
    }

    fun insertFilmsAndCollectionCrossRef(kinopoiskId: Int, nameCollection: String) {
        viewModelScope.launch(Dispatchers.IO) {
            insertFilmsAndCollectionCrossRefUseCase.insertFilmsAndCollectionCrossRef(
                FilmsAndCollectionsCrossRef(
                    kinopoiskId,
                    nameCollection
                )
            )
        }
    }

    fun deleteCrossReference(kinopoiskId: Int, nameCollection: String) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteCrossReferenceUseCase.deleteCrossReference(
                FilmsAndCollectionsCrossRef(
                    kinopoiskId,
                    nameCollection
                )
            )
        }
    }

    val getAllCollectionsDataBase = this.getAllCollectionsDataBaseUseCase.getAllCollectionsDataBase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    val getAllCrossReference = this.getAllCrossReferenceUseCase.getAllCrossReference().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    val getFilmsIsViewed = this.getFilmsIsViewedUseCase.getFilmsIsViewed().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )
}