import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import di.Koin

fun main() = application {
    Koin.initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "Versati",
    ) {
        App()
    }
}