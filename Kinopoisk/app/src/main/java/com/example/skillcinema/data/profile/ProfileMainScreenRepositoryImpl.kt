package com.example.skillcinema.data.profile

import com.example.skillcinema.domain.profile.ProfileMainScreenRepository
import com.example.skillcinema.entity.profile.Collections
import com.example.skillcinema.entity.profile.CollectionsAndFilms
import com.example.skillcinema.entity.profile.FilmEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileMainScreenRepositoryImpl @Inject constructor(private val filmDao: FilmDao): ProfileMainScreenRepository {
    override fun getFilmsIsViewed(): Flow<List<FilmEntity>> {
        return filmDao.getFilmsIsViewed()
    }

    override suspend fun insertNewCollection(collections: Collections) {
         filmDao.insertNewCollection(collections)
    }

    override fun getAllCollectionsDataBase(): Flow<List<CollectionsAndFilms>> {
        return filmDao.getAllCollectionsDataBase()
    }

    override fun deleteCollectionFromDataBase(collectionName: String) {
        filmDao.deleteCollectionFromDataBase(collectionName)
    }

    override fun deleteFilmFromCollections(collectionName: String) {
        filmDao.deleteFilmFromCollections(collectionName)
    }

}