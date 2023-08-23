package com.example.skillcinema.data.search

import com.example.skillcinema.domain.search.CountryAndGenresRepository
import com.example.skillcinema.entity.search.CountryAndGenresForSearchEntity
import com.example.skillcinema.ui.home.RetrofitServices
import javax.inject.Inject

class CountryAndGenresRepositoryImpl @Inject constructor() : CountryAndGenresRepository {
    override suspend fun searchCountryAndGenresForSearch(): CountryAndGenresForSearchEntity {
        return RetrofitServices.retrofitService.searchCountryAndGenresForSearch()
    }
}