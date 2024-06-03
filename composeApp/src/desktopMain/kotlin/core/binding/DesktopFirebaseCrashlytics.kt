package core.binding

class DesktopFirebaseCrashlytics : FirebaseCrashlytics {

    override fun error(error: Throwable) {
//        на desktop не завезли firebase :(
//        crashlytics.recordException(error)
    }
}