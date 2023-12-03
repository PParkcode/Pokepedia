package com.example.pokepedia.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PokepediaApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
