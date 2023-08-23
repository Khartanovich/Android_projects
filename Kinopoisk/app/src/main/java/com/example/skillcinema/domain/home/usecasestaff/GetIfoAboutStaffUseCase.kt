package com.example.skillcinema.domain.home.usecasestaff

import com.example.skillcinema.domain.home.StaffScreenRepository
import com.example.skillcinema.entity.home.staff.InformationAboutStaffEntity
import javax.inject.Inject

class GetIfoAboutStaffUseCase @Inject constructor(private val staffScreenRepository: StaffScreenRepository) {
    suspend fun getInfoAboutStaff(staffId: Int): InformationAboutStaffEntity {
        return staffScreenRepository.getInfoAboutStaff(staffId)
    }
}