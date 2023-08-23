package com.example.skillcinema.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skillcinema.data.home.HomeMainScreenRepositoryImpl
import com.example.skillcinema.domain.home.usecase.GetPopularMovieUseCase
import com.example.skillcinema.domain.home.usecase.GetPremiersMovieUseCase
import com.example.skillcinema.domain.home.usecase.GetTopMovieUseCase
import com.example.skillcinema.entity.home.MovieEntity
import com.example.skillcinema.entity.home.NameAndListMovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainHomePageViewModel @Inject constructor(
    private val getPremiersMovieUseCase: GetPremiersMovieUseCase,
    private val getTopMovieUseCase: GetTopMovieUseCase,
    private val getPopularMovieUseCase: GetPopularMovieUseCase
) : ViewModel() {

    private val _moviesPremier = MutableStateFlow<List<MovieEntity>>(emptyList())
    val moviesPremier = _moviesPremier.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private var _moviesTop = MutableStateFlow<List<MovieEntity>>(emptyList())
    val moviesTop = _moviesTop.asStateFlow()

    private var _moviesPopular = MutableStateFlow<List<MovieEntity>>(emptyList())
    val moviesPopular = _moviesPopular.asStateFlow()

    init {
        loadPremieres()
        getTopFilms()
        getPopularFilms()
    }

    private fun loadPremieres() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _isLoading.value = true
                getPremiersMovieUseCase.getPremieresFilms(2023, "NOVEMBER")
            }.fold(
                onSuccess = { _moviesPremier.value = it },
                onFailure = { Log.d("MyLog", it.message ?: "") }
            )
            _isLoading.value = false
        }
    }

    fun getTopFilms() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                getTopMovieUseCase.getTopFilms(FIRST_PAGE)
            }.fold(
                onSuccess = { _moviesTop.value = it },
                onFailure = { Log.d("MyLog", it.message ?: "") }
            )
        }
    }

    fun getPopularFilms() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                getPopularMovieUseCase.getPopularFilms(FIRST_PAGE)
            }.fold(
                onSuccess = { _moviesPopular.value = it },
                onFailure = { Log.d("MyLog", it.message ?: "") }
            )
        }
    }

    val combineFlow = combine(
        moviesTop, moviesPopular
    ) { t, p ->
        val combineList = listOf(t, p)
        val nameList = listOf<String>(TOP, POPULAR)
        val result = combineList.mapIndexed { index, movies ->
            NameAndListMovieEntity(nameList[index], movies)
        }
        result
    }

    companion object {
        private const val FIRST_PAGE = 1
        private const val TOP = "Топ"
        private const val POPULAR = "Популярные"
    }
}