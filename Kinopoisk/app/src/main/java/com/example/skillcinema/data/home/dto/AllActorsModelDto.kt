package com.example.skillcinema.data.home.dto


import com.example.skillcinema.entity.home.AllActorsEntity
import com.google.gson.annotations.SerializedName

data class AllActorsModelDto(
    @SerializedName("description")
    override val description: String?,
    @SerializedName("nameEn")
    override val nameEn: String?,
    @SerializedName("nameRu")
    override val nameRu: String?,
    @SerializedName("posterUrl")
    override val posterUrl: String,
    @SerializedName("professionKey")
    override val professionKey: String,
    @SerializedName("professionText")
    override val professionText: String,
    @SerializedName("staffId")
    override val staffId: Int
) : AllActorsEntity