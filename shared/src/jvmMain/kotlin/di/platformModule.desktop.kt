package di

import core.binding.FirebaseAnalytics
import core.binding.FirebaseCrashlytics
import core.database.AppDatabase
import core.database.PlatformDatabase
import core.firebase.DesktopFirebaseSDK
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<AppDatabase> { PlatformDatabase.getDatabase() }
        single<FirebaseCrashlytics> { DesktopFirebaseSDK.firebaseCrashlytics }
        single<FirebaseAnalytics> { DesktopFirebaseSDK.firebaseAnalytics }
    }