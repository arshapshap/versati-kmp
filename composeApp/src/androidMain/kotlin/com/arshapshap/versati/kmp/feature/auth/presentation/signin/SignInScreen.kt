package com.arshapshap.versati.kmp.feature.auth.presentation.signin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.arshapshap.versati.kmp.core.navigation.features.AuthFeature
import com.arshapshap.versati.kmp.core.navigation.features.SettingsFeature
import com.arshapshap.versati.kmp.core.navigation.state.AppBarState
import feature.auth.presentation.signin.contract.SignInSideEffect
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

internal object SignInScreen {

    @Composable
    fun Content(
        navController: NavHostController,
        appBarConfigure: (AppBarState) -> Unit
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
            appBarConfigure(getAppBarState())
        }
        SignInContent(state = state, viewModel = viewModel)
    }

    private fun getAppBarState() = AppBarState(
        currentRoute = AuthFeature.SignIn.route,
        showArrowBack = true,
        showBottomBar = false
    )
}