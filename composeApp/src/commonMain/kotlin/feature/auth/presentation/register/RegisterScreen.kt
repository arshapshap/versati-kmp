package feature.auth.presentation.register

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
import feature.auth.presentation.register.contract.RegisterSideEffect

internal object RegisterScreen {

    @Composable
    fun Content(
        navController: NavHostController,
        appBarConfigure: (AppBarState) -> Unit
    ) {
        val viewModel = getViewModel<RegisterViewModel>()
        val state by viewModel.collectAsState()
        viewModel.collectSideEffect {
            when (it) {
                RegisterSideEffect.NavigateToSignIn -> navController.popBackStack()
                RegisterSideEffect.NavigateToSettings -> navController.navigate(SettingsFeature.Settings.destination()) {
                    popUpTo(SettingsFeature.Settings.route) {
                        inclusive = true
                    }
                }
            }
        }

        SideEffect {
            appBarConfigure(getAppBarState())
        }
        RegisterContent(
            state = state,
            onEmailChange = viewModel::updateEmail,
            onPasswordChange = viewModel::updatePassword,
            onRegister = viewModel::register,
            onSwitchToSignIn = viewModel::navigateToSignIn
        )
    }

    private fun getAppBarState() = AppBarState(
        currentRoute = AuthFeature.Register.route,
        showArrowBack = true,
        showBottomBar = false
    )
}