package com.example.skillcinema.domain.home

import com.example.skillcinema.entity.home.AllActorsEntity
import com.example.skillcinema.entity.home.ImagesGalleryEntity
import com.example.skillcinema.entity.home.InformationAboutFilmEntity
import com.example.skillcinema.entity.home.SimilarFilmsEntity
import com.example.skillcinema.entity.profile.FilmEntity
import com.example.skillcinema.entity.profile.FilmsAndCollections
import com.example.skillcinema.entity.profile.FilmsAndCollectionsCrossRef
import kotlinx.coroutines.flow.Flow

interface InfoAboutFilmScreenRepository {
    suspend fun getInformation(id: Int): InformationAboutFilmEntity?
    suspend fun getAllActors(id: Int): List<AllActorsEntity>
    suspend fun getImagesFilm(id: Int, type: String, page: Int): ImagesGalleryEntity
    suspend fun getSimilarFilms(id: Int): SimilarFilmsEntity
    fun deleteCrossReference(crossReference: FilmsAndCollectionsCrossRef)
    fun deleteFilmFromDataBase(kinopoiskId: Int)
    fun getAllCrossReference(): Flow<List<FilmsAndCollectionsCrossRef>>
    fun getAllFilmsDataBase(): Flow<List<FilmsAndCollections>>
    suspend fun insertFilmsAndCollectionCrossRef(crossReference: FilmsAndCollectionsCrossRef)
    suspend fun insertFilm(film: FilmEntity)
    fun updateIsViewed(kinopoiskId: Int, isViewed: Boolean)
}