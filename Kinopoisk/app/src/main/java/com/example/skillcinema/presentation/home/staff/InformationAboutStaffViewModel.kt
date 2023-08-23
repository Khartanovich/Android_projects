package com.example.skillcinema.presentation.home.staff

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skillcinema.data.home.dto.InformationAboutFilmDto
import com.example.skillcinema.domain.home.usecasestaff.GetIfoAboutStaffUseCase
import com.example.skillcinema.domain.home.usecasestaff.GetInfoAboutFilmForStaffUseCase
import com.example.skillcinema.entity.home.InformationAboutFilmEntity
import com.example.skillcinema.entity.home.staff.InformationAboutStaffEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InformationAboutStaffViewModel @Inject constructor(
    private val staffUseCase: GetIfoAboutStaffUseCase,
    private val infoFilmUseCase: GetInfoAboutFilmForStaffUseCase
) : ViewModel() {

    private val _infoAboutStaff = MutableStateFlow<InformationAboutStaffEntity?>(null)
    val infoAboutStaff = _infoAboutStaff.asStateFlow()

    private val _bestFilmStaff = MutableStateFlow<List<InformationAboutFilmEntity?>>(emptyList())
    val bestFilmStaff = _bestFilmStaff.asStateFlow()

    private var bestFilms: MutableList<InformationAboutFilmEntity> = emptyList<InformationAboutFilmDto>().toMutableList()

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

    fun getBestFilmStaff() {
        viewModelScope.launch(Dispatchers.IO) {
            val changeRatingFilm = _infoAboutStaff.value?.films?.onEach {
                if (it.rating?.toDoubleOrNull() == null){
                    it.rating = 0.0.toString()
                }
            }
            changeRatingFilm?.sortedByDescending { it.rating?.toDouble() }?.take(10)?.onEach {
                kotlin.runCatching {
                    infoFilmUseCase.getFilm(it.filmId)
                }.fold(
                    onSuccess = {
                        if (it != null) {
                            bestFilms.add(it)
                        };
                    },
                    onFailure = { Log.d("MyLog", it.message ?: "") }
                )
            }
            _bestFilmStaff.value = bestFilms
        }
    }
}