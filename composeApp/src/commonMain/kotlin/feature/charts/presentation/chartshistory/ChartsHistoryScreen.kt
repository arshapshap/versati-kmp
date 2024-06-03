package feature.charts.presentation.chartshistory

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import core.navigation.features.ChartsFeature
import core.navigation.state.AppBarState
import core.presentation_utils.collectAsState
import core.presentation_utils.collectSideEffect
import core.presentation_utils.getViewModel
import feature.charts.presentation.chartshistory.contract.ChartsHistorySideEffect
import main.ScaffoldOptions
import org.jetbrains.compose.resources.stringResource
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.charts_history
import versati.composeapp.generated.resources.clear_history


internal object ChartsHistoryScreen {

    @Composable
    fun Content(
        navController: NavHostController,
        scaffoldOptions: ScaffoldOptions
    ) {
        val viewModel = getViewModel<ChartsHistoryViewModel>()
        val state by viewModel.collectAsState()

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is ChartsHistorySideEffect.OpenChart ->
                    navController.navigate(ChartsFeature.ChartGeneration.destination(id = sideEffect.id))
            }
        }

        val appBarState = getAppBarState(
            showClearButton = state.history.isNotEmpty(),
            onClearClick = viewModel::clearHistoryUnconfirmed
        )
        SideEffect {
            scaffoldOptions.appBarConfigure(appBarState)
        }
        ChartsHistoryContent(
            state = state,
            onChartClick = viewModel::openChart,
            onClearHistory = viewModel::clearHistoryConfirmed,
            onCancelClearing = viewModel::cancelClear
        )
    }

    @Composable
    private fun getAppBarState(
        showClearButton: Boolean,
        onClearClick: () -> Unit
    ) = AppBarState(
        currentRoute = ChartsFeature.ChartsHistory.route,
        title = stringResource(Res.string.charts_history),
        showArrowBack = true,
        actions = {
            if (showClearButton)
                IconButton(onClick = onClearClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(Res.string.clear_history)
                    )
                }
        }
    )
}