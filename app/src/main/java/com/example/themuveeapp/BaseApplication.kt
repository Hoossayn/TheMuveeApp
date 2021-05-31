package com.example.themuveeapp

import android.app.Application
import com.google.android.play.core.splitcompat.SplitCompatApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: SplitCompatApplication() {
    override fun onCreate() {
        super.onCreate()
    }
}