package com.example.skillcinema.presentation.home.allmoviesinselection

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.skillcinema.domain.home.usecase.GetPopularMovieUseCase
import com.example.skillcinema.domain.home.usecase.GetPremiersMovieUseCase
import com.example.skillcinema.domain.home.usecase.GetTopMovieUseCase
import com.example.skillcinema.entity.home.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllMovieInSelectionViewModel @Inject constructor(
    private val getPremiersMovieUseCase: GetPremiersMovieUseCase,
    private val getTopMovieUseCase: GetTopMovieUseCase,
    private val getPopularMovieUseCase: GetPopularMovieUseCase
) : ViewModel() {

    private val _moviesPremier = MutableStateFlow<List<MovieEntity>>(emptyList())
    val moviesPremier = _moviesPremier.asStateFlow()

    fun loadPremieres() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                getPremiersMovieUseCase.getPremieresFilms(2023, "NOVEMBER")
            }.fold(
                onSuccess = { _moviesPremier.value = it },
                onFailure = { Log.d("MyLog", it.message ?: "") }
            )
        }
    }

    val loadTopFilms: Flow<PagingData<MovieEntity>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { TopFilmsPagingSource(getTopMovieUseCase) }
    ).flow.cachedIn(viewModelScope)

    val loadPopularFilms: Flow<PagingData<MovieEntity>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { PopularFilmsPagingSource(getPopularMovieUseCase) }
    ).flow.cachedIn(viewModelScope)
}