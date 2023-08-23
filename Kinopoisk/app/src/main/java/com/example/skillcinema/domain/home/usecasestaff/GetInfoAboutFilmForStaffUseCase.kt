package com.example.skillcinema.domain.home.usecasestaff

import com.example.skillcinema.data.home.dto.InformationAboutFilmDto
import com.example.skillcinema.domain.home.StaffScreenRepository
import com.example.skillcinema.entity.home.InformationAboutFilmEntity
import javax.inject.Inject

class GetInfoAboutFilmForStaffUseCase @Inject constructor(private val staffScreenRepository: StaffScreenRepository) {
    suspend fun getFilm(filmId: Int): InformationAboutFilmEntity?{
        return staffScreenRepository.getFilm(filmId)
    }
}