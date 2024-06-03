package di

import android.content.Context
import core.binding.FirebaseAnalytics
import core.binding.FirebaseCrashlytics
import core.database.AppDatabase
import core.database.PlatformDatabase
import core.firebase.AndroidFirebaseSDK
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<AppDatabase> { PlatformDatabase.getDatabase(get<Context>()) }
        single<FirebaseCrashlytics> { AndroidFirebaseSDK.firebaseCrashlytics }
        single<FirebaseAnalytics> { AndroidFirebaseSDK.firebaseAnalytics }
    }