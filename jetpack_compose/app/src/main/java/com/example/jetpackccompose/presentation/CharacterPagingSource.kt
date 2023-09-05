package com.example.jetpackccompose.presentation

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jetpackccompose.entity.CharacterEntity

class CharacterPagingSource(
    private val viewModel: MyViewModel
): PagingSource<Int, CharacterEntity>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterEntity>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterEntity> {
        val page = params.key ?: 0
        return kotlin.runCatching {
                viewModel.loadAllCharacters(page)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if (it.isEmpty()) null else page + 1
                )
            },
            onFailure = { LoadResult.Error(it)}
        )
    }

    companion object {
        fun pagerCharacters(viewModel: MyViewModel) = Pager(
            config = PagingConfig(20),
            pagingSourceFactory = { CharacterPagingSource(viewModel) }
        )
    }
}