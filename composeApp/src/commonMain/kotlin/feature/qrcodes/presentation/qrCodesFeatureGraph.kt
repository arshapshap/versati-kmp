package feature.qrcodes.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import core.navigation.features.QRCodesFeature
import feature.qrcodes.presentation.qrcodegeneration.QRCodeGenerationScreen
import feature.qrcodes.presentation.qrcodeshistory.QRCodesHistoryScreen
import main.ScaffoldOptions

fun NavGraphBuilder.qrCodesFeatureGraph(
    navController: NavHostController,
    scaffoldOptions: ScaffoldOptions
) {
    navigation(
        route = QRCodesFeature.featureRoute,
        startDestination = QRCodesFeature.QRCodeGeneration.route
    ) {
        composable(
            route = QRCodesFeature.QRCodeGeneration.route,
            arguments = QRCodesFeature.QRCodeGeneration.arguments
        ) {
            QRCodeGenerationScreen.Content(
                navController = navController,
                id = it.arguments?.getLong(QRCodesFeature.QRCodeGeneration.idArgument),
                scaffoldOptions = scaffoldOptions
            )
        }
        composable(
            route = QRCodesFeature.QRCodesHistory.route
        ) {
            QRCodesHistoryScreen.Content(
                navController = navController,
                scaffoldOptions = scaffoldOptions
            )
        }
    }
}