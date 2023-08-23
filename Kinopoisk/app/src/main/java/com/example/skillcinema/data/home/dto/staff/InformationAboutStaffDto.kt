package com.example.skillcinema.data.home.dto.staff

import com.example.skillcinema.entity.home.staff.InformationAboutStaffEntity
import com.google.gson.annotations.SerializedName

data class InformationAboutStaffDto(
    @SerializedName("age")
    override val age: Int?,
    @SerializedName("birthday")
    override val birthday: String?,
    @SerializedName("birthplace")
    override val birthplace: String?,
    @SerializedName("death")
    override val death: String?,
    @SerializedName("deathplace")
    override val deathplace: String?,
    @SerializedName("facts")
    override val facts: List<String?>?,
    @SerializedName("films")
    override val films: List<FilmStaffDto>?,
    @SerializedName("growth")
    override val growth: String?,
    @SerializedName("hasAwards")
    override val hasAwards: Int?,
    @SerializedName("nameEn")
    override val nameEn: String?,
    @SerializedName("nameRu")
    override val nameRu: String?,
    @SerializedName("personId")
    override val personId: Int?,
    @SerializedName("posterUrl")
    override val posterUrl: String?,
    @SerializedName("profession")
    override val profession: String?,
    @SerializedName("sex")
    override val sex: String?,
    @SerializedName("spouses")
    override val spouses: List<Any?>?,
    @SerializedName("webUrl")
    override val webUrl: String?
) : InformationAboutStaffEntity
