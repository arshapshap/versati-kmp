package core.navigation.features

import androidx.navigation.NavType
import androidx.navigation.navArgument
import core.navigation.base.BaseFeature

sealed class QRCodesFeature(screenName: String) : BaseFeature(featureRoute, screenName) {

    companion object : BaseFeature.Companion("qrcodes_feature")

    data object QRCodeGeneration : QRCodesFeature("qrcode_generation") {

        const val idArgument = "id"
        val arguments = listOf(
            navArgument(idArgument) {
                type = NavType.LongType
                defaultValue = 0L
            }
        )

        fun destination(id: Long = 0L) = destination(mapOf(arguments[0] to id))
        override val route get() = route(arguments)
    }

    data object QRCodesHistory : QRCodesFeature("history")
}