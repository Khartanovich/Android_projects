package com.example.jetpackccompose.presentation

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jetpackccompose.entity.LocationEntity

class LocationPagingSource(
    private val viewModel: MyViewModel
): PagingSource<Int, LocationEntity>() {

    override fun getRefreshKey(state: PagingState<Int, LocationEntity>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationEntity> {
        val page = params.key ?: 0
        return kotlin.runCatching {
            viewModel.loadAllLocations(page)
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

    companion object{
        fun pagerLocations(viewModel: MyViewModel) = Pager(
            config = PagingConfig(20),
            pagingSourceFactory = { LocationPagingSource(viewModel) }
        )
    }
}