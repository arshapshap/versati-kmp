package com.arshapshap.versati.kmp

import android.app.Application
import com.google.firebase.FirebaseApp
import core.binding.AndroidFirebaseAnalytics
import core.binding.AndroidFirebaseCrashlytics
import core.firebase.AndroidFirebaseSDK
import di.Koin
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        AndroidFirebaseSDK.init(
            crashlytics = AndroidFirebaseCrashlytics(),
            analytics = AndroidFirebaseAnalytics()
        )
        Koin.initKoin {
            androidContext(applicationContext)
        }
    }
}