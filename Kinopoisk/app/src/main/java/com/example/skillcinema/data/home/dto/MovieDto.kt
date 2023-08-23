package com.example.skillcinema.data.home.dto

import com.example.skillcinema.entity.*
import com.example.skillcinema.entity.home.CountryEntity
import com.example.skillcinema.entity.home.GenreEntity
import com.example.skillcinema.entity.home.MovieEntity
import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("kinopoiskId")
    override val kinopoiskId: Int,
    @SerializedName("nameRu")
    override val nameRu: String,
    @SerializedName("posterUrl")
    override val posterUrl: String,
    @SerializedName("posterUrlPreview")
    override val posterUrlPreview: String,
    @SerializedName("genres")
    override val genres: List<GenreDto>,
    @SerializedName("premiereRu")
    override val premiereRu: String,
    @SerializedName("countries")
    override val countries: List<CountryDto>,
    @SerializedName("filmId")
    override val filmId: Int,
) : MovieEntity

data class GenreDto(
    @SerializedName("genre")
    override val genre: String
) : GenreEntity

data class CountryDto(
    @SerializedName("country")
    override val country: String
) : CountryEntity