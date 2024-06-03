package core.firebase

import core.binding.FirebaseCrashlytics

object AndroidFirebaseSDK {

    private var _firebaseCrashlytics: FirebaseCrashlytics? = null
    val firebaseCrashlytics: FirebaseCrashlytics
        get() = _firebaseCrashlytics!!

    fun init(crashlyticsBinding: FirebaseCrashlytics) {
        _firebaseCrashlytics = crashlyticsBinding
    }
}