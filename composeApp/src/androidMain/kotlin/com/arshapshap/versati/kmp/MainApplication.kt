package com.arshapshap.versati.kmp

import android.app.Application
import core.binding.AndroidFirebaseCrashlytics
import core.firebase.AndroidFirebaseSDK
import di.Koin
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidFirebaseSDK.init(
            crashlyticsBinding = AndroidFirebaseCrashlytics()
        )
        Koin.initKoin {
            androidContext(applicationContext)
        }
    }
}