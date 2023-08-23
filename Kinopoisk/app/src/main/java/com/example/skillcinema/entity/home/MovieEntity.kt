package com.example.skillcinema.entity.home


interface MovieEntity {
    val kinopoiskId: Int
    val nameRu: String
    val posterUrl: String
    val posterUrlPreview: String
    val genres: List<GenreEntity>
    val premiereRu: String
    val countries: List<CountryEntity>
    val filmId: Int
}

interface GenreEntity {
    val genre: String
}

interface CountryEntity{
    val country: String
}
