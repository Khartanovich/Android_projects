package com.example.skillcinema.domain.search

interface SettingsRepository {
    fun getCountryName(): String?
    fun getCountryId(): Int?
    fun saveCountryName(countryName: String)
    fun saveCountryId(countryId: Int)
    fun getGenresName(): String?
    fun saveGenresName(genresName: String)
    fun getGenresId(): Int?
    fun saveGenresId(genresId: Int)
    fun getYearFrom(): Int?
    fun saveYearFrom(yearFrom: Int)
    fun getYearTo(): Int?
    fun saveYearTo(yearTo: Int)
    fun getRatingFrom(): Int?
    fun saveRatingFrom(ratingFrom: Int)
    fun getRatingTo(): Int?
    fun saveRatingTo(ratingTo: Int)
    fun getOrder(): String?
    fun saveOrder(order: String)
    fun getType(): String?
    fun saveType(type: String)
    fun clearAllSettingsSearch()
}