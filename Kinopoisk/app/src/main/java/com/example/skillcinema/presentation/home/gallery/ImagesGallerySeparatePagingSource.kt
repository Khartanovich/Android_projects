package com.example.skillcinema.presentation.home.gallery

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.skillcinema.data.home.dto.ItemGalleryDto
import com.example.skillcinema.domain.home.usecaseinfofilm.GetImagesFilmUseCase
import com.example.skillcinema.entity.home.ItemGalleryEntity
import javax.inject.Inject

class ImagesGallerySeparatePagingSource @Inject constructor(
    private val getImagesFilmUseCase: GetImagesFilmUseCase,
    val id: Int,
    val type: String
) : PagingSource<Int, ItemGalleryEntity>() {
    override fun getRefreshKey(state: PagingState<Int, ItemGalleryEntity>): Int? = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemGalleryEntity> {
        val page = params.key ?: FIRST_PAGE
        return kotlin.runCatching {
            getImagesFilmUseCase.getImagesFilm(id, type, page)
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