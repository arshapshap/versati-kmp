package feature.settings.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import core.navigation.features.SettingsFeature
import feature.settings.presentation.settings.SettingsScreen
import main.ScaffoldOptions

fun NavGraphBuilder.settingsFeatureGraph(
    navController: NavHostController,
    scaffoldOptions: ScaffoldOptions
) {
    navigation(
        route = SettingsFeature.featureRoute,
        startDestination = SettingsFeature.Settings.route
    ) {
        composable(
            route = SettingsFeature.Settings.route
        ) {
            SettingsScreen.Content(
                navController = navController,
                scaffoldOptions = scaffoldOptions
            )
        }
    }
}