package com.example.skillcinema.presentation.search.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skillcinema.data.search.CountryAndGenresForSearchDto
import com.example.skillcinema.domain.search.usecase.GetCountryAndGenresUseCase
import com.example.skillcinema.entity.search.CountryAndGenresForSearchEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsSearchCountryOrGenresViewModel @Inject constructor(
    private val countryAndGenresUseCase: GetCountryAndGenresUseCase
) : ViewModel() {

    private val _countryAndGenres = MutableStateFlow<CountryAndGenresForSearchEntity?>(null)
    val countryAndGenres = _countryAndGenres.asStateFlow()

    init {
        searchCountryAndGenresForSearch()
    }

    private fun searchCountryAndGenresForSearch(){
        viewModelScope.launch(Dispatchers.IO){
            kotlin.runCatching {
                countryAndGenresUseCase.searchCountryAndGenresForSearch()
            }.fold(
                onSuccess = { _countryAndGenres.value = it },
                onFailure = { Log.d("MyLog", it.message ?: "")}
            )
        }
    }
}