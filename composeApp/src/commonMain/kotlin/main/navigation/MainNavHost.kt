package main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import feature.auth.presentation.authFeatureGraph
import feature.charts.presentation.chartsFeatureGraph
import feature.imageparsing.presentation.imageParsingFeatureGraph
import feature.qrcodes.presentation.qrCodesFeatureGraph
import feature.settings.presentation.settingsFeatureGraph
import main.ScaffoldOptions

@Composable
internal fun MainNavHost(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String,
    scaffoldOptions: ScaffoldOptions
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        authFeatureGraph(navController, scaffoldOptions)
        chartsFeatureGraph(navController, scaffoldOptions)
        imageParsingFeatureGraph(navController, scaffoldOptions)
        qrCodesFeatureGraph(navController, scaffoldOptions)
        settingsFeatureGraph(navController, scaffoldOptions)
    }
}