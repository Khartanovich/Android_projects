package com.example.jetpackccompose.data.model.character


import com.google.gson.annotations.SerializedName

data class AllCharacter(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<CharacterDto>
)