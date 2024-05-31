package feature.charts.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import core.navigation.features.ChartsFeature
import core.navigation.state.AppBarState
import feature.charts.presentation.chartgeneration.ChartGenerationScreen
import feature.charts.presentation.chartshistory.ChartsHistoryScreen

fun NavGraphBuilder.chartsFeatureGraph(
    navController: NavHostController,
    appBarConfigure: (AppBarState) -> Unit
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
                appBarConfigure = appBarConfigure
            )
        }
        composable(
            route = ChartsFeature.ChartsHistory.route
        ) {
            ChartsHistoryScreen.Content(
                navController = navController,
                appBarConfigure = appBarConfigure
            )
        }
    }
}