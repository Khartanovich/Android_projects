package com.example.jetpackccompose.data.model.location


import com.example.jetpackccompose.entity.LocationEntity
import com.google.gson.annotations.SerializedName

data class InfoLocationDto(
    @SerializedName("created")
    override val created: String,
    @SerializedName("dimension")
    override val dimension: String,
    @SerializedName("id")
    override val id: Int,
    @SerializedName("name")
    override val name: String,
    @SerializedName("residents")
    override val residents: List<String>,
    @SerializedName("type")
    override val type: String,
    @SerializedName("url")
    override val url: String
) : LocationEntity