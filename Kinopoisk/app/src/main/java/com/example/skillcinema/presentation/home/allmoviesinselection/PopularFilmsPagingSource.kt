package com.example.skillcinema.presentation.home.allmoviesinselection

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.skillcinema.domain.home.usecase.GetPopularMovieUseCase
import com.example.skillcinema.entity.home.MovieEntity
import javax.inject.Inject

class PopularFilmsPagingSource @Inject constructor(val useCase: GetPopularMovieUseCase) : PagingSource<Int, MovieEntity>() {
    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        val page = params.key ?: FIRST_PAGE
        return kotlin.runCatching {
            useCase.getPopularFilms(page)
        }.fold(
            onSuccess = { LoadResult.Page(
                data = it,
                prevKey = null,
                nextKey = if (it.isEmpty()) null else page + 1
            )},
            onFailure = { LoadResult.Error(it)}
        )
    }

    private companion object {
        private const val FIRST_PAGE = 1
    }
}