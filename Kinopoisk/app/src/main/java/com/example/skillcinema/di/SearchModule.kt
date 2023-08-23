package com.example.skillcinema.di

import com.example.skillcinema.data.search.CountryAndGenresRepositoryImpl
import com.example.skillcinema.data.search.SearchScreenRepositoryImpl
import com.example.skillcinema.data.search.SettingsSharedPrefRepository
import com.example.skillcinema.domain.search.CountryAndGenresRepository
import com.example.skillcinema.domain.search.SearchScreenRepository
import com.example.skillcinema.domain.search.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SearchModule {
    @Provides
    @Singleton
    fun provideCountryAndGenresRepository(): CountryAndGenresRepository{
        return CountryAndGenresRepositoryImpl()
    }
    @Provides
    @Singleton
    fun provideSearchScreenRepository(): SearchScreenRepository {
        return SearchScreenRepositoryImpl()
    }
    @Provides
    @Singleton
    fun provideSettingsRepository(): SettingsRepository{
        return SettingsSharedPrefRepository()
    }
}