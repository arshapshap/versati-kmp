package feature.settings.presentation.settings

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
import feature.settings.presentation.SettingsViewModel
import feature.settings.presentation.contract.SettingsSideEffect
import main.ScaffoldOptions
import org.jetbrains.compose.resources.stringResource
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.settings

internal object SettingsScreen {

    @Composable
    fun Content(
        navController: NavHostController,
        scaffoldOptions: ScaffoldOptions
    ) {
        val viewModel = getViewModel<SettingsViewModel>()
        val state by viewModel.collectAsState()
        viewModel.collectSideEffect {
            when (it) {
                SettingsSideEffect.NavigateToSignIn -> navController.navigate(AuthFeature.SignIn.destination())
            }
        }

        val appBarState = getAppBarState()
        SideEffect {
            scaffoldOptions.appBarConfigure(appBarState)
        }
        SettingsContent(
            state = state,
            onLogInClick = viewModel::navigateToSignIn,
            onLogOutClick = viewModel::logOutUnconfirmed,
            onCancelLogOut = viewModel::cancelLogOut,
            onLogOut = viewModel::logOutConfirmed,
        )
    }

    @Composable
    private fun getAppBarState() = AppBarState(
        currentRoute = SettingsFeature.Settings.route,
        title = stringResource(Res.string.settings),
        showArrowBack = true,
        showBottomBar = false
    )
}