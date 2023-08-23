package com.example.skillcinema.data.home

import com.example.skillcinema.domain.home.StaffScreenRepository
import com.example.skillcinema.entity.home.InformationAboutFilmEntity
import com.example.skillcinema.entity.home.staff.InformationAboutStaffEntity
import com.example.skillcinema.ui.home.RetrofitServices
import javax.inject.Inject

class StaffScreenRepositoryImpl@Inject constructor() : StaffScreenRepository {
    override suspend fun getInfoAboutStaff(staffId: Int): InformationAboutStaffEntity {
        return RetrofitServices.retrofitService.getInformationAboutStaff(staffId)
    }

    override suspend fun getFilm(filmId: Int): InformationAboutFilmEntity? {
        return RetrofitServices.retrofitService.getInfoAboutFilm(filmId)
    }
}