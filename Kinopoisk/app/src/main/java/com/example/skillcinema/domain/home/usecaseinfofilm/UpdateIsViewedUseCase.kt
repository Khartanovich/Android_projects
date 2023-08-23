package com.example.skillcinema.domain.home.usecaseinfofilm

import com.example.skillcinema.domain.home.InfoAboutFilmScreenRepository
import javax.inject.Inject

class UpdateIsViewedUseCase @Inject constructor(private val repository: InfoAboutFilmScreenRepository) {
    fun updateIsViewed(kinopoiskId: Int, isViewed: Boolean) {
        repository.updateIsViewed(kinopoiskId, isViewed)
    }
}