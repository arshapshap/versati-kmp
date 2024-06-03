package feature.imageparsing.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import core.navigation.features.ImageParsingFeature
import feature.imageparsing.presentation.history.ParsingHistoryScreen
import feature.imageparsing.presentation.parsing.ParsingScreen
import main.ScaffoldOptions

fun NavGraphBuilder.imageParsingFeatureGraph(
    navController: NavHostController,
    scaffoldOptions: ScaffoldOptions
) {
    navigation(
        route = ImageParsingFeature.featureRoute,
        startDestination = ImageParsingFeature.Parsing.route
    ) {
        composable(
            route = ImageParsingFeature.Parsing.route,
            arguments = ImageParsingFeature.Parsing.arguments
        ) {
            ParsingScreen.Content(
                navController = navController,
                id = it.arguments?.getLong(ImageParsingFeature.Parsing.idArgument),
                scaffoldOptions = scaffoldOptions
            )
        }

        composable(
            route = ImageParsingFeature.ParsingHistory.route
        ) {
            ParsingHistoryScreen.Content(
                navController = navController,
                scaffoldOptions = scaffoldOptions
            )
        }
    }
}