package com.twitty.assignment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AssignmentApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}