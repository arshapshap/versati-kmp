package feature.settings.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import core.navigation.features.SettingsFeature
import core.navigation.state.AppBarState
import feature.settings.presentation.settings.SettingsScreen

fun NavGraphBuilder.settingsFeatureGraph(
    navController: NavHostController,
    appBarConfigure: (AppBarState) -> Unit
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
                appBarConfigure = appBarConfigure
            )
        }
    }
}