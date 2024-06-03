package core.binding

import com.google.firebase.crashlytics.FirebaseCrashlytics

class AndroidFirebaseCrashlytics : core.binding.FirebaseCrashlytics {

    private val crashlytics by lazy {
        FirebaseCrashlytics.getInstance()
    }

    override fun error(error: Throwable) {
        crashlytics.recordException(error)
    }
}