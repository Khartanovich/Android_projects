package com.example.skillcinema.data

import android.app.Application
import com.example.skillcinema.data.profile.FilmDataBase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class App : Application() {
}