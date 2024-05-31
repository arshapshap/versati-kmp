package com.arshapshap.versati.kmp

import android.app.Application
import di.Koin
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Koin.initKoin {
            androidContext(applicationContext)
        }
    }
}