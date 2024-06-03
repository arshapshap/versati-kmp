package core.firebase

import core.binding.FirebaseAnalytics
import core.binding.FirebaseCrashlytics

object DesktopFirebaseSDK {

    private var _firebaseCrashlytics: FirebaseCrashlytics? = null
    val firebaseCrashlytics: FirebaseCrashlytics
        get() = _firebaseCrashlytics!!

    private var _firebaseAnalytics: FirebaseAnalytics? = null
    val firebaseAnalytics: FirebaseAnalytics
        get() = _firebaseAnalytics!!

    fun init(crashlytics: FirebaseCrashlytics, analytics: FirebaseAnalytics) {
        _firebaseCrashlytics = crashlytics
        _firebaseAnalytics = analytics
    }
}