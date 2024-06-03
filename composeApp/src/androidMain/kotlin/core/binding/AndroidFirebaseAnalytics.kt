package core.binding

import androidx.core.os.bundleOf
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

class AndroidFirebaseAnalytics : FirebaseAnalytics {

    private val analytics by lazy {
        Firebase.analytics
    }

    override fun recordLaunchScreen(screenName: String) {
        analytics.logEvent(FirebaseAnalytics.PARAM_LAUNCH, bundleOf("screen" to screenName))
    }
}