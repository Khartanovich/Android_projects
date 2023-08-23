package com.example.skillcinema.data.home

import com.example.skillcinema.data.profile.FilmDao
import com.example.skillcinema.domain.home.InfoAboutFilmScreenRepository
import com.example.skillcinema.entity.home.AllActorsEntity
import com.example.skillcinema.entity.home.ImagesGalleryEntity
import com.example.skillcinema.entity.home.InformationAboutFilmEntity
import com.example.skillcinema.entity.home.SimilarFilmsEntity
import com.example.skillcinema.entity.profile.FilmEntity
import com.example.skillcinema.entity.profile.FilmsAndCollections
import com.example.skillcinema.entity.profile.FilmsAndCollectionsCrossRef
import com.example.skillcinema.ui.home.RetrofitServices
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InfoAboutFilmScreenRepositoryIml @Inject constructor(private val filmDao: FilmDao): InfoAboutFilmScreenRepository {
    override suspend fun getInformation(id: Int): InformationAboutFilmEntity? {
        return RetrofitServices.retrofitService.getInfoAboutFilm(id)
    }

    override suspend fun getAllActors(id: Int): List<AllActorsEntity> {
        return RetrofitServices.retrofitService.getAllActors(id)
    }

    override suspend fun getImagesFilm(id: Int, type: String, page: Int): ImagesGalleryEntity {
        return RetrofitServices.retrofitService.getImagesFilm(id, type, page)
    }

    override suspend fun getSimilarFilms(id: Int): SimilarFilmsEntity {
        return RetrofitServices.retrofitService.getSimilarFilms(id)
    }

    override fun deleteCrossReference(crossReference: FilmsAndCollectionsCrossRef) {
        filmDao.deleteCrossReference(crossReference)
    }

    override fun deleteFilmFromDataBase(kinopoiskId: Int) {
        filmDao.deleteFilmFromDataBase(kinopoiskId)
    }

    override fun getAllCrossReference(): Flow<List<FilmsAndCollectionsCrossRef>> {
        return filmDao.getAllCrossReference()
    }

    override fun getAllFilmsDataBase(): Flow<List<FilmsAndCollections>> {
        return filmDao.getAllFilmsDataBase()
    }

    override suspend fun insertFilmsAndCollectionCrossRef(crossReference: FilmsAndCollectionsCrossRef) {
        filmDao.insertFilmsAndCollectionCrossRef(crossReference)
    }

    override suspend fun insertFilm(film: FilmEntity) {
        filmDao.insertFilm(film)
    }

    override fun updateIsViewed(kinopoiskId: Int, isViewed: Boolean) {
        filmDao.updateIsViewed(kinopoiskId, isViewed)
    }
}