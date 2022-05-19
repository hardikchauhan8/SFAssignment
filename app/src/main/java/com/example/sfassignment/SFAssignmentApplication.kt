package com.example.sfassignment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SFAssignmentApplication : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}