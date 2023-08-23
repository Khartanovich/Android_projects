package com.example.skillcinema.entity.home


interface SimilarFilmsEntity {
    val total: Int
    val items: List<SimilarFilmEntity>
}