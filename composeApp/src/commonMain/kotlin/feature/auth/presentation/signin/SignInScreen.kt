package feature.auth.presentation.signin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import core.navigation.features.AuthFeature
import core.navigation.features.SettingsFeature
import core.navigation.state.AppBarState
import core.presentation_utils.collectAsState
import core.presentation_utils.collectSideEffect
import core.presentation_utils.getViewModel
import feature.auth.presentation.signin.contract.SignInSideEffect
import main.ScaffoldOptions

internal object SignInScreen {

    @Composable
    fun Content(
        navController: NavHostController,
        scaffoldOptions: ScaffoldOptions
    ) {
        val viewModel = getViewModel<SignInViewModel>()
        val state by viewModel.collectAsState()
        viewModel.collectSideEffect {
            when (it) {
                SignInSideEffect.NavigateToRegistration -> navController.navigate(AuthFeature.Register.destination())
                SignInSideEffect.NavigateToSettings -> navController.navigate(SettingsFeature.Settings.destination()) {
                    popUpTo(SettingsFeature.Settings.route) {
                        inclusive = true
                    }
                }
            }
        }

        SideEffect {
            scaffoldOptions.appBarConfigure(getAppBarState())
        }
        SignInContent(
            state = state,
            onEmailChange = viewModel::updateEmail,
            onPasswordChange = viewModel::updatePassword,
            onSignIn = viewModel::signIn,
            onSwitchToRegister = viewModel::navigateToRegistration
        )
    }

    private fun getAppBarState() = AppBarState(
        currentRoute = AuthFeature.SignIn.route,
        showArrowBack = true,
        showBottomBar = false
    )
}