package com.example.skillcinema.data.home.dto


import com.example.skillcinema.entity.home.InformationAboutFilmEntity
import com.google.gson.annotations.SerializedName

data class InformationAboutFilmDto(
    @SerializedName("completed")
    override val completed: Boolean?,
    @SerializedName("countries")
    override val countries: List<CountryDto?>?,
    @SerializedName("coverUrl")
    override val coverUrl: String?,
    @SerializedName("description")
    override val description: String?,
    @SerializedName("editorAnnotation")
    override val editorAnnotation: String?,
    @SerializedName("endYear")
    override val endYear: Int?,
    @SerializedName("filmLength")
    override val filmLength: Int?,
    @SerializedName("genres")
    override val genres: List<GenreDto?>?,
    @SerializedName("has3D")
    override val has3D: Boolean?,
    @SerializedName("hasImax")
    override val hasImax: Boolean?,
    @SerializedName("imdbId")
    override val imdbId: String?,
    @SerializedName("isTicketsAvailable")
    override val isTicketsAvailable: Boolean?,
    @SerializedName("kinopoiskId")
    override val kinopoiskId: Int?,
    @SerializedName("lastSync")
    override val lastSync: String?,
    @SerializedName("logoUrl")
    override val logoUrl: String?,
    @SerializedName("nameEn")
    override val nameEn: String?,
    @SerializedName("nameOriginal")
    override val nameOriginal: String?,
    @SerializedName("nameRu")
    override val nameRu: String?,
    @SerializedName("posterUrl")
    override val posterUrl: String?,
    @SerializedName("posterUrlPreview")
    override val posterUrlPreview: String?,
    @SerializedName("productionStatus")
    override val productionStatus: String?,
    @SerializedName("ratingAgeLimits")
    override val ratingAgeLimits: String?,
    @SerializedName("ratingAwait")
    override val ratingAwait: Double?,
    @SerializedName("ratingAwaitCount")
    override val ratingAwaitCount: Int?,
    @SerializedName("ratingFilmCritics")
    override val ratingFilmCritics: Double?,
    @SerializedName("ratingFilmCriticsVoteCount")
    override val ratingFilmCriticsVoteCount: Int?,
    @SerializedName("ratingGoodReview")
    override val ratingGoodReview: Double?,
    @SerializedName("ratingGoodReviewVoteCount")
    override val ratingGoodReviewVoteCount: Int?,
    @SerializedName("ratingImdb")
    override val ratingImdb: Double?,
    @SerializedName("ratingImdbVoteCount")
    override val ratingImdbVoteCount: Int?,
    @SerializedName("ratingKinopoisk")
    override val ratingKinopoisk: Double?,
    @SerializedName("ratingKinopoiskVoteCount")
    override val ratingKinopoiskVoteCount: Int?,
    @SerializedName("ratingMpaa")
    override val ratingMpaa: String?,
    @SerializedName("ratingRfCritics")
    override val ratingRfCritics: Double?,
    @SerializedName("ratingRfCriticsVoteCount")
    override val ratingRfCriticsVoteCount: Int?,
    @SerializedName("reviewsCount")
    override val reviewsCount: Int?,
    @SerializedName("serial")
    override val serial: Boolean?,
    @SerializedName("shortDescription")
    override val shortDescription: String?,
    @SerializedName("shortFilm")
    override val shortFilm: Boolean?,
    @SerializedName("slogan")
    override val slogan: String?,
    @SerializedName("startYear")
    override val startYear: Int?,
    @SerializedName("type")
    override val type: String?,
    @SerializedName("webUrl")
    override val webUrl: String?,
    @SerializedName("year")
    override val year: Int?
) : InformationAboutFilmEntity