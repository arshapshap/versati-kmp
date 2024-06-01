package feature.imageparsing.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import core.navigation.features.ImageParsingFeature
import core.navigation.state.AppBarState
import feature.imageparsing.presentation.history.ParsingHistoryScreen
import feature.imageparsing.presentation.parsing.ParsingScreen

fun NavGraphBuilder.imageParsingFeatureGraph(
    navController: NavHostController,
    appBarConfigure: (AppBarState) -> Unit
) {
    navigation(
        route = ImageParsingFeature.featureRoute,
        startDestination = ImageParsingFeature.Parsing.route
    ) {
        composable(
            route = ImageParsingFeature.Parsing.route,
            arguments = ImageParsingFeature.Parsing.arguments
        ) {
//            BackHandler { }

            ParsingScreen.Content(
                navController = navController,
                id = it.arguments?.getLong(ImageParsingFeature.Parsing.idArgument),
                appBarConfigure = appBarConfigure
            )
        }

        composable(
            route = ImageParsingFeature.ParsingHistory.route
        ) {
            ParsingHistoryScreen.Content(
                navController = navController,
                appBarConfigure = appBarConfigure
            )
        }
    }
}