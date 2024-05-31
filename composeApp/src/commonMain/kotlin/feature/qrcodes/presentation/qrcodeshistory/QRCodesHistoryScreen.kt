package feature.qrcodes.presentation.qrcodeshistory

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import core.navigation.features.QRCodesFeature
import core.navigation.state.AppBarState
import core.presentation_utils.collectAsState
import core.presentation_utils.collectSideEffect
import core.presentation_utils.getViewModel
import feature.qrcodes.presentation.qrcodeshistory.contract.QRCodesHistorySideEffect
import org.jetbrains.compose.resources.stringResource
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.clear_history
import versati.composeapp.generated.resources.qrcodes_history


internal object QRCodesHistoryScreen {

    @Composable
    fun Content(
        navController: NavHostController,
        appBarConfigure: (AppBarState) -> Unit
    ) {
        val viewModel = getViewModel<QRCodesHistoryViewModel>()
        val state by viewModel.collectAsState()

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is QRCodesHistorySideEffect.OpenQRCode ->
                    navController.navigate(QRCodesFeature.QRCodeGeneration.destination(id = sideEffect.id))
            }
        }

        val appBarState = getAppBarState(
            showClearButton = state.history.isNotEmpty(),
            onClearClick = viewModel::clearHistoryUnconfirmed
        )
        SideEffect {
            appBarConfigure(appBarState)
        }
        QRCodesHistoryContent(state = state, viewModel = viewModel)
    }

    @Composable
    private fun getAppBarState(
        showClearButton: Boolean,
        onClearClick: () -> Unit
    ) = AppBarState(
        currentRoute = QRCodesFeature.QRCodesHistory.route,
        title = stringResource(Res.string.qrcodes_history),
        showArrowBack = true,
        actions = {
            if (showClearButton) {
                IconButton(onClick = onClearClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(Res.string.clear_history)
                    )
                }
            }
        }
    )
}