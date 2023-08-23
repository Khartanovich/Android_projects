package com.example.skillcinema.domain.home.usecaseinfofilm

import com.example.skillcinema.domain.home.InfoAboutFilmScreenRepository
import javax.inject.Inject

class DeleteFilmFromDataBaseUseCase @Inject constructor(private val repository: InfoAboutFilmScreenRepository) {
    fun deleteFilmFromDataBase(kinopoiskId: Int){
        repository.deleteFilmFromDataBase(kinopoiskId)
    }
}