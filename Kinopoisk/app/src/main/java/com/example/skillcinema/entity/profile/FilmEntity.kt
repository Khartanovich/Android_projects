package com.example.skillcinema.entity.profile

import androidx.room.*


@Entity(tableName = "films_table")
data class FilmEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "kinopoiskId")
    val kinopoiskId: Int,
    @ColumnInfo(name = "nameRu")
    val nameRu: String,
    @ColumnInfo(name = "posterUrlPreview")
    val posterUrlPreview: String,
    @ColumnInfo(name = "genres")
    val genres: String,
    @ColumnInfo(name = "year")
    val year: Int,
    @ColumnInfo(name = "ratingImdb")
    val ratingImdb: Double?,
    @ColumnInfo(name = "isViewed")
    val isViewed: Boolean,
)

@Entity(tableName = "collections")
data class Collections(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "collectionName")
    val collectionName: String,
)

@Entity(tableName = "cross_reference",primaryKeys = ["kinopoiskId", "collectionName"])
data class FilmsAndCollectionsCrossRef(
    @ColumnInfo(name = "kinopoiskId")
    val kinopoiskId: Int,
    @ColumnInfo(name = "collectionName")
    val collectionName: String
)

data class FilmsAndCollections(
    @Embedded
    val film: FilmEntity,
    @Relation(
        parentColumn = "kinopoiskId",
        entityColumn = "collectionName",
        associateBy = Junction(FilmsAndCollectionsCrossRef::class)
    )
    val listCollections: List<Collections>?
)

data class CollectionsAndFilms(
    @Embedded
    val collection: Collections,
    @Relation(
        parentColumn = "collectionName",
        entityColumn = "kinopoiskId",
        associateBy = Junction(FilmsAndCollectionsCrossRef::class)
    )
    val films: List<FilmEntity>?
)



