package com.example.skillcinema.presentation.search

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.skillcinema.domain.search.usecase.SearchFilmUseCase
import com.example.skillcinema.entity.home.MovieEntity
import javax.inject.Inject

class SearchPagingSource @Inject constructor(
    val searchFilmUseCase: SearchFilmUseCase,
    val keyword: String?,
    val country: Int,
    val genres: Int,
    val ratingFrom: Int,
    val ratingTo: Int,
    val yearFrom: Int,
    val yearTo: Int,
    val order: String,
    val type: String
) : PagingSource<Int, MovieEntity>() {

    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        val page = params.key ?: FIRST_PAGE
        return kotlin.runCatching {
            searchFilmUseCase.searchFilms(
                page,
                keyword,
                country,
                genres,
                ratingFrom,
                ratingTo,
                yearFrom,
                yearTo,
                order, type
            )
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it.items,
                    prevKey = null,
                    nextKey = if (it.items.isEmpty()) null else page + 1
                )
            },
            onFailure = {
                Log.d("Mylog", "throwable $it")
                LoadResult.Error(it)
            }
        )
    }

    private companion object {
        private const val FIRST_PAGE = 1
    }
}