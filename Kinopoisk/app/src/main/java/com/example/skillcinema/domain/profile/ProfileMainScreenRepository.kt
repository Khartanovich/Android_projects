package com.example.skillcinema.domain.profile

import com.example.skillcinema.entity.profile.Collections
import com.example.skillcinema.entity.profile.CollectionsAndFilms
import com.example.skillcinema.entity.profile.FilmEntity
import kotlinx.coroutines.flow.Flow

interface ProfileMainScreenRepository {
    fun getFilmsIsViewed(): Flow<List<FilmEntity>>
    suspend fun insertNewCollection(collections: Collections)
    fun getAllCollectionsDataBase(): Flow<List<CollectionsAndFilms>>
    fun deleteCollectionFromDataBase(collectionName: String)
    fun deleteFilmFromCollections(collectionName: String)
}