package com.example.skillcinema.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.skillcinema.domain.search.usecase.SearchFilmUseCase
import com.example.skillcinema.entity.home.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchFilmUseCase: SearchFilmUseCase) : ViewModel() {
    fun searchFilm(
        keyword: String?,
        country: Int,
        genres: Int,
        ratingFrom: Int,
        ratingTo: Int,
        yearFrom: Int,
        yearTo: Int,
        order: String,
        type: String
    ): Flow<PagingData<MovieEntity>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            SearchPagingSource(
                searchFilmUseCase, keyword ?: "",
                country, genres,
                ratingFrom, ratingTo,
                yearFrom, yearTo,
                order, type
            )
        }
    ).flow.cachedIn(viewModelScope)
}