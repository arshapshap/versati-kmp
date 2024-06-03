package feature.charts.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import core.navigation.features.ChartsFeature
import feature.charts.presentation.chartgeneration.ChartGenerationScreen
import feature.charts.presentation.chartshistory.ChartsHistoryScreen
import main.ScaffoldOptions

fun NavGraphBuilder.chartsFeatureGraph(
    navController: NavHostController,
    scaffoldOptions: ScaffoldOptions
) {
    navigation(
        route = ChartsFeature.featureRoute,
        startDestination = ChartsFeature.ChartGeneration.route
    ) {
        composable(
            route = ChartsFeature.ChartGeneration.route,
            arguments = ChartsFeature.ChartGeneration.arguments
        ) {
            ChartGenerationScreen.Content(
                navController = navController,
                id = it.arguments?.getLong(ChartsFeature.ChartGeneration.idArgument),
                scaffoldOptions = scaffoldOptions
            )
        }
        composable(
            route = ChartsFeature.ChartsHistory.route
        ) {
            ChartsHistoryScreen.Content(
                navController = navController,
                scaffoldOptions = scaffoldOptions
            )
        }
    }
}