package com.example.jetpackccompose.di

import com.example.jetpackccompose.data.MyRepositoryImpl
import com.example.jetpackccompose.domain.MyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainHiltModule {

    @Provides
    @Singleton
    fun provideMyRepository(): MyRepository {
        return MyRepositoryImpl()
    }
}