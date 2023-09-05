package com.example.jetpackccompose.data.model.location


import com.google.gson.annotations.SerializedName

data class Locations(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val infoLocations: List<InfoLocationDto>
)