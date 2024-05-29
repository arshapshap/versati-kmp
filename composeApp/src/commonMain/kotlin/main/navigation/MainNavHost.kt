package main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import core.navigation.state.AppBarState
import feature.auth.presentation.authFeatureGraph

@Composable
internal fun MainNavHost(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String,
    appBarConfigure: (AppBarState) -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        authFeatureGraph(navController, appBarConfigure)
//        chartsFeatureGraph(navController, appBarConfigure)
//        imageParsingFeatureGraph(navController, appBarConfigure)
//        qrCodesFeatureGraph(navController, appBarConfigure)
//        settingsFeatureGraph(navController, appBarConfigure)
    }
}