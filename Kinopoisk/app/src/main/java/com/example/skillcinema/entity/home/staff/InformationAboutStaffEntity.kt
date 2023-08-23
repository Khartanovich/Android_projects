package com.example.skillcinema.entity.home.staff


interface InformationAboutStaffEntity {
    val age: Int?
    val birthday: String?
    val birthplace: String?
    val death: String?
    val deathplace: String?
    val facts: List<String?>?
    val films: List<FilmStaffEntity>?
    val growth: String?
    val hasAwards: Int?
    val nameEn: String?
    val nameRu: String?
    val personId: Int?
    val posterUrl: String?
    val profession: String?
    val sex: String?
    val spouses: List<Any?>?
    val webUrl: String?
}