package com.example.skillcinema.domain.search

import com.example.skillcinema.entity.search.CountryAndGenresForSearchEntity

interface CountryAndGenresRepository {
    suspend fun searchCountryAndGenresForSearch(): CountryAndGenresForSearchEntity
}