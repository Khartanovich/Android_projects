package com.example.skillcinema.domain.home.usecaseinfofilm

import com.example.skillcinema.domain.home.InfoAboutFilmScreenRepository
import com.example.skillcinema.entity.home.ImagesGalleryEntity
import javax.inject.Inject

class GetImagesFilmUseCase @Inject constructor(private val repository: InfoAboutFilmScreenRepository) {
    suspend fun getImagesFilm(id: Int, type: String, page: Int): ImagesGalleryEntity{
        return repository.getImagesFilm(id, type, page)
    }
}