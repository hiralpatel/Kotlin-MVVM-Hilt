package com.hpandro.album

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AlbumApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}