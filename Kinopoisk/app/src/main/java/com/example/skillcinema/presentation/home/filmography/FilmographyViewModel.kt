package com.example.skillcinema.presentation.home.filmography

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skillcinema.domain.home.usecasestaff.GetIfoAboutStaffUseCase
import com.example.skillcinema.entity.home.staff.InformationAboutStaffEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmographyViewModel @Inject constructor(
    private val staffUseCase: GetIfoAboutStaffUseCase
) : ViewModel() {

    private val _infoAboutStaff = MutableStateFlow<InformationAboutStaffEntity?>(null)
    val infoAboutStaff = _infoAboutStaff.asStateFlow()

    fun getInfoAboutStaff(staffId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                staffUseCase.getInfoAboutStaff(staffId)
            }.fold(
                onSuccess = { _infoAboutStaff.value = it },
                onFailure = { Log.d("MyLog", it.message ?: "") }
            )
        }
    }
}