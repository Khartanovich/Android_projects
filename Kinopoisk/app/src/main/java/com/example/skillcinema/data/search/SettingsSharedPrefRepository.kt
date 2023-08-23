package com.example.skillcinema.data.search

import android.content.Context
import android.content.SharedPreferences
import com.example.skillcinema.domain.search.SettingsRepository
import javax.inject.Inject

class SettingsSharedPrefRepository @Inject constructor() : SettingsRepository {
    private lateinit var preference: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    fun initSettingsSharedPrefRepository(context: Context) {
        preference = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        editor = preference.edit()
    }

    override fun getCountryName(): String? {
        return preference.getString(PREF_KEY_COUNTRY_NAME, "Франция")
    }

    override fun getCountryId(): Int? {
        return preference.getInt(PREF_KEY_COUNTRY_ID, 3)
    }

    override fun saveCountryName(countryName: String) {
        editor.putString(PREF_KEY_COUNTRY_NAME, countryName)
        editor.apply()
    }

    override fun saveCountryId(countryId: Int) {
        editor.putInt(PREF_KEY_COUNTRY_ID, countryId)
        editor.apply()
    }

    override fun getGenresName(): String? {
        return preference.getString(PREF_KEY_GENRES, "фантастика")
    }

    override fun saveGenresName(genresName: String) {
        editor.putString(PREF_KEY_GENRES, genresName)
        editor.apply()
    }

    override fun getGenresId(): Int? {
        return preference.getInt(PREF_KEY_GENRES_ID, 6)
    }

    override fun saveGenresId(genresId: Int) {
        editor.putInt(PREF_KEY_GENRES_ID, genresId)
        editor.apply()
    }

    override fun getYearFrom(): Int? {
        return preference.getInt(PREF_KEY_YEAR_FROM, 1986)
    }

    override fun saveYearFrom(yearFrom: Int) {
        editor.putInt(PREF_KEY_YEAR_FROM, yearFrom)
        editor.apply()
    }

    override fun getYearTo(): Int? {
        return preference.getInt(PREF_KEY_YEAR_TO, 2023)
    }

    override fun saveYearTo(yearTo: Int) {
        editor.putInt(PREF_KEY_YEAR_TO, yearTo)
        editor.apply()
    }

    override fun getRatingFrom(): Int? {
        return preference.getInt(PREF_KEY_RATING_FROM, 1)
    }

    override fun saveRatingFrom(ratingFrom: Int) {
        editor.putInt(PREF_KEY_RATING_FROM, ratingFrom)
        editor.apply()
    }

    override fun getRatingTo(): Int? {
        return preference.getInt(PREF_KEY_RATING_TO, 10)
    }

    override fun saveRatingTo(ratingTo: Int) {
        editor.putInt(PREF_KEY_RATING_TO, ratingTo)
        editor.apply()
    }

    override fun getOrder(): String? {
        return preference.getString(PREF_KEY_ORDER, "RATING")
    }

    override fun saveOrder(order: String) {
        editor.putString(PREF_KEY_ORDER, order)
        editor.apply()
    }

    override fun getType(): String? {
        return preference.getString(PREF_KEY_TYPE, "ALL")
    }

    override fun saveType(type: String) {
        editor.putString(PREF_KEY_TYPE, type)
    }

    override fun clearAllSettingsSearch() {
        editor.clear()
        editor.apply()
    }

    companion object {
        private const val SHARED_PREF_NAME = "Settings repository"
        private const val PREF_KEY_COUNTRY_NAME = "CountryName"
        private const val PREF_KEY_COUNTRY_ID = "CountryId"
        private const val PREF_KEY_GENRES = "Genres"
        private const val PREF_KEY_GENRES_ID = "GenresID"
        private const val PREF_KEY_YEAR_FROM = "YearFrom"
        private const val PREF_KEY_YEAR_TO = "YearTo"
        private const val PREF_KEY_RATING_FROM = "RatingFrom"
        private const val PREF_KEY_RATING_TO = "RatingTo"
        private const val PREF_KEY_ORDER = "Order"
        private const val PREF_KEY_TYPE = "Type"
    }
}