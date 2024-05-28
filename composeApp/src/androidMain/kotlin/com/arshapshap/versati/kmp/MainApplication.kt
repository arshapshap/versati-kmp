package com.arshapshap.versati.kmp

import android.app.Application
import com.arshapshap.versati.kmp.feature.auth.di.authFeatureModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(
//                appModule,
//                databaseModule,
//                networkModule,
//                storageModule,
                authFeatureModule,
//                chartsFeatureModule,
//                imageParsingFeatureModule,
//                qrCodesFeatureModule,
//                settingsFeatureModule,
            )
        }
    }
}