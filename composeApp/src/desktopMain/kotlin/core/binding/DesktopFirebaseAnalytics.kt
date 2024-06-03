package core.binding

class DesktopFirebaseAnalytics : FirebaseAnalytics {

    override fun recordLaunchScreen(screenName: String) {
        println("${FirebaseAnalytics.PARAM_LAUNCH} $screenName")
    }
}