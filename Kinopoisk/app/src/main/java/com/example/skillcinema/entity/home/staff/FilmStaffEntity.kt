package com.example.skillcinema.entity.home.staff

import com.google.gson.annotations.SerializedName

interface FilmStaffEntity {
    val filmId: Int
    val nameRu: String
    val nameEn: String
    var rating: String?
    val general: Boolean
    val description: String
    val professionKey: ProfessionEntity?
}