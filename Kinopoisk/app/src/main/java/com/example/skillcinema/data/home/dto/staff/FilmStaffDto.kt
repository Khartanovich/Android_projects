package com.example.skillcinema.data.home.dto.staff

import com.example.skillcinema.entity.home.staff.FilmStaffEntity
import com.example.skillcinema.entity.home.staff.ProfessionEntity
import com.google.gson.annotations.SerializedName

data class FilmStaffDto(
    @SerializedName("filmId")
    override val filmId: Int,
    @SerializedName("nameRu")
    override val nameRu: String,
    @SerializedName("nameEn")
    override val nameEn: String,
    @SerializedName("rating")
    override var rating: String?,
    @SerializedName("general")
    override val general: Boolean,
    @SerializedName("description")
    override val description: String,
    @SerializedName("professionKey")
    override val professionKey: ProfessionEntity?
) : FilmStaffEntity
