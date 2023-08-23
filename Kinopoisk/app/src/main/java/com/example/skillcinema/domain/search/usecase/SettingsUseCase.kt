package com.example.skillcinema.domain.search.usecase

import com.example.skillcinema.domain.search.SettingsRepository
import javax.inject.Inject

class SettingsUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {
    fun getCountryName(): String? {
        return settingsRepository.getCountryName()
    }
    fun getCountryId(): Int? {
        return settingsRepository.getCountryId()
    }
    fun saveCountryName(countryName: String) {
        settingsRepository.saveCountryName(countryName)
    }
    fun saveCountryId(countryId: Int) {
        settingsRepository.saveCountryId(countryId)
    }
    fun getGenresName(): String? {
        return settingsRepository.getGenresName()
    }
    fun saveGenresName(genresName: String){
        settingsRepository.saveGenresName(genresName)
    }
    fun getGenresId(): Int? {
        return settingsRepository.getGenresId()
    }
    fun saveGenresId(genresId: Int){
        settingsRepository.saveGenresId(genresId)
    }
    fun getYearFrom(): Int? {
        return settingsRepository.getYearFrom()
    }
    fun saveYearFrom(yearFrom: Int){
        settingsRepository.saveYearFrom(yearFrom)
    }
    fun getYearTo(): Int? {
        return settingsRepository.getYearTo()
    }
    fun saveYearTo(yearTo: Int) {
        settingsRepository.saveYearTo(yearTo)
    }
    fun getRatingFrom(): Int? {
        return settingsRepository.getRatingFrom()
    }
    fun saveRatingFrom(ratingFrom: Int){
        settingsRepository.saveRatingFrom(ratingFrom)
    }
    fun getRatingTo(): Int? {
        return settingsRepository.getRatingTo()
    }
    fun saveRatingTo(ratingTo: Int) {
        settingsRepository.saveRatingTo(ratingTo)
    }
    fun getOrder(): String? {
        return settingsRepository.getOrder()
    }
    fun saveOrder(order: String) {
        settingsRepository.saveOrder(order)
    }
    fun getType(): String? {
        return settingsRepository.getType()
    }
    fun saveType(type: String) {
        settingsRepository.saveType(type)
    }
    fun clearAllSettingsSearch() {
        settingsRepository.clearAllSettingsSearch()
    }
}