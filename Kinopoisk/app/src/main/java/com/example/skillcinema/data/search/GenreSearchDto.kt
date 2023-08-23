package com.example.skillcinema.data.search

import com.example.skillcinema.entity.search.OverallCountryAndGenres
import com.google.gson.annotations.SerializedName

data class GenreSearchDto(
    @SerializedName("genre")
    override val name: String,
    @SerializedName("id")
    override val id: Int
) : OverallCountryAndGenres