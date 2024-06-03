package feature.auth.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import core.navigation.features.AuthFeature
import feature.auth.presentation.register.RegisterScreen
import feature.auth.presentation.signin.SignInScreen
import main.ScaffoldOptions

fun NavGraphBuilder.authFeatureGraph(
    navController: NavHostController,
    scaffoldOptions: ScaffoldOptions
) {
    navigation(
        route = AuthFeature.featureRoute,
        startDestination = AuthFeature.SignIn.route
    ) {
        composable(
            route = AuthFeature.Register.route
        ) {
            RegisterScreen.Content(
                navController = navController,
                scaffoldOptions = scaffoldOptions
            )
        }
        composable(
            route = AuthFeature.SignIn.route
        ) {
            SignInScreen.Content(
                navController = navController,
                scaffoldOptions = scaffoldOptions
            )
        }
    }
}