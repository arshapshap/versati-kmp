import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import core.binding.DesktopFirebaseAnalytics
import core.binding.DesktopFirebaseCrashlytics
import core.firebase.DesktopFirebaseSDK
import di.Koin

fun main() = application {
    DesktopFirebaseSDK.init(
        crashlytics = DesktopFirebaseCrashlytics(),
        analytics = DesktopFirebaseAnalytics()
    )
    Koin.initKoin()
    val state = rememberWindowState(placement = WindowPlacement.Maximized)
    Window(
        onCloseRequest = ::exitApplication,
        title = "Versati",
        state = state
    ) {
        App()
    }
}