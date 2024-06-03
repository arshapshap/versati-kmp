package core.binding

interface FirebaseAnalytics {

    companion object {
        const val PARAM_LAUNCH = "launch"
    }

    fun recordLaunchScreen(screenName: String)
}