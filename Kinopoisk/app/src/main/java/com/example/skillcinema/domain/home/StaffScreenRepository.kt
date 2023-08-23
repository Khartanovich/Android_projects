package com.example.skillcinema.domain.home

import com.example.skillcinema.entity.home.InformationAboutFilmEntity
import com.example.skillcinema.entity.home.staff.InformationAboutStaffEntity

interface StaffScreenRepository {
    suspend fun getInfoAboutStaff(staffId: Int) : InformationAboutStaffEntity
    suspend fun getFilm(filmId: Int): InformationAboutFilmEntity?
}