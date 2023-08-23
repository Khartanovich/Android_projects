package com.example.skillcinema.presentation.search.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsYearViewModel @Inject constructor() : ViewModel() {
    private var _yearsFrom = MutableStateFlow<List<Int>>((1998..2009).map { it })
    val yearsFrom = _yearsFrom.asStateFlow()

    private var _yearsTo = MutableStateFlow<List<Int>>((2010..2022).map { it })
    val yearsTo = _yearsTo.asStateFlow()

    fun getYearsFromUp(years: List<Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            _yearsFrom.value = years
        }
    }

    fun getYearsFromBack(years: List<Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            _yearsFrom.value = years
        }
    }

    fun getYearsToUp(years: List<Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            _yearsTo.value = years
        }
    }

    fun getYearsToBack(years: List<Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            _yearsTo.value = years
        }
    }
}