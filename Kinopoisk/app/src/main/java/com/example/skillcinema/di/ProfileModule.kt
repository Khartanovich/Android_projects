package com.example.skillcinema.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.skillcinema.data.profile.FilmDao
import com.example.skillcinema.data.profile.FilmDataBase
import com.example.skillcinema.data.profile.FilmDataBaseCallback
import com.example.skillcinema.data.profile.ProfileMainScreenRepositoryImpl
import com.example.skillcinema.domain.profile.ProfileMainScreenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProfileModule {


    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context, provider: Provider<FilmDao>): FilmDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            FilmDataBase::class.java,
            "film_data_base"
        )
            .addCallback(FilmDataBaseCallback(provider))
            .build()
    }

    @Provides
    @Singleton
    fun provideFilmDao(db: FilmDataBase): FilmDao{
        return db.filmDao()
    }

    @Provides
    @Singleton
    fun provideProfileMainScreenRepository(filmDao: FilmDao) : ProfileMainScreenRepository{
        return ProfileMainScreenRepositoryImpl(filmDao)
    }



}