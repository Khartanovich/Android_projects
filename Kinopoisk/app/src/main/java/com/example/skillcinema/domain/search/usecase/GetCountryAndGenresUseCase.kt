package com.example.skillcinema.domain.search.usecase

import com.example.skillcinema.domain.search.CountryAndGenresRepository
import com.example.skillcinema.entity.search.CountryAndGenresForSearchEntity
import javax.inject.Inject

class GetCountryAndGenresUseCase @Inject constructor(private val repository: CountryAndGenresRepository) {
    suspend fun searchCountryAndGenresForSearch(): CountryAndGenresForSearchEntity {
        return repository.searchCountryAndGenresForSearch()
    }
}