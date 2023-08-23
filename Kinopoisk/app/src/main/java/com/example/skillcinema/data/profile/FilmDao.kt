package com.example.skillcinema.data.profile

import androidx.room.*
import com.example.skillcinema.entity.profile.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFilm(film: FilmEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewCollection(collections: Collections)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFilmsAndCollectionCrossRef(crossReference: FilmsAndCollectionsCrossRef)

    @Transaction
    @Query("SELECT * FROM films_table")
    fun getAllFilmsDataBase(): Flow<List<FilmsAndCollections>>

    @Transaction
    @Query("SELECT * FROM collections")
    fun getAllCollectionsDataBase(): Flow<List<CollectionsAndFilms>>

    @Query("SELECT * FROM cross_reference")
    fun getAllCrossReference(): Flow<List<FilmsAndCollectionsCrossRef>>

    @Query("DELETE FROM films_table WHERE kinopoiskId = :kinopoiskId")
    fun deleteFilmFromDataBase(kinopoiskId: Int)

    @Query("DELETE FROM collections WHERE collectionName = :collectionName")
    fun deleteCollectionFromDataBase(collectionName: String)

    @Delete
    fun deleteCrossReference(crossReference: FilmsAndCollectionsCrossRef)

    @Query("DELETE FROM cross_reference WHERE collectionName = :collectionName")
    fun deleteFilmFromCollections(collectionName: String)

    @Query("UPDATE films_table SET isViewed = :isViewed WHERE kinopoiskId = :kinopoiskId")
    fun updateIsViewed(kinopoiskId: Int, isViewed: Boolean)

    @Query("SELECT * FROM films_table WHERE isViewed = 1")
    fun getFilmsIsViewed(): Flow<List<FilmEntity>>
}