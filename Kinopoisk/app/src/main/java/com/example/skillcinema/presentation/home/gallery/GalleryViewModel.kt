package com.example.skillcinema.presentation.home.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.skillcinema.data.home.dto.ItemGalleryDto
import com.example.skillcinema.domain.home.usecaseinfofilm.GetImagesFilmUseCase
import com.example.skillcinema.entity.home.ItemGalleryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val getImagesFilmUseCase: GetImagesFilmUseCase) :
    ViewModel() {
    fun getImagesGallery(id: Int, type: String): Flow<PagingData<ItemGalleryEntity>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            ImagesGallerySeparatePagingSource(getImagesFilmUseCase, id, type)
        }
    ).flow.cachedIn(viewModelScope)
}