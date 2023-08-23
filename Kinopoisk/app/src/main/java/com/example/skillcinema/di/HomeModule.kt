package com.example.skillcinema.di

import com.example.skillcinema.data.home.HomeMainScreenRepositoryImpl
import com.example.skillcinema.data.home.InfoAboutFilmScreenRepositoryIml
import com.example.skillcinema.data.home.StaffScreenRepositoryImpl
import com.example.skillcinema.data.profile.FilmDao
import com.example.skillcinema.domain.home.HomeMainScreenRepository
import com.example.skillcinema.domain.home.InfoAboutFilmScreenRepository
import com.example.skillcinema.domain.home.StaffScreenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeModule {

    @Provides
    @Singleton
    fun provideHomeMainScreenRepository() : HomeMainScreenRepository {
        return HomeMainScreenRepositoryImpl()
    }
    @Provides
    @Singleton
    fun provideInfoAboutFilmScreenRepository(filmDao: FilmDao) : InfoAboutFilmScreenRepository {
        return InfoAboutFilmScreenRepositoryIml(filmDao)
    }
    @Provides
    @Singleton
    fun provideStaffScreenRepository() : StaffScreenRepository {
        return StaffScreenRepositoryImpl()
    }
}