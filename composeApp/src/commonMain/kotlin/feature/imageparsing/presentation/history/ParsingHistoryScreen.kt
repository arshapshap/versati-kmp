package feature.imageparsing.presentation.history

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import core.navigation.features.ImageParsingFeature
import core.navigation.state.AppBarState
import core.presentation_utils.collectAsState
import core.presentation_utils.collectSideEffect
import core.presentation_utils.getViewModel
import feature.imageparsing.presentation.history.contract.ParsingHistorySideEffect
import main.ScaffoldOptions
import org.jetbrains.compose.resources.stringResource
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.clear_history
import versati.composeapp.generated.resources.parsing_history

internal object ParsingHistoryScreen {

    @Composable
    fun Content(
        navController: NavHostController,
        scaffoldOptions: ScaffoldOptions
    ) {
        val viewModel = getViewModel<ParsingHistoryViewModel>()
        val state by viewModel.collectAsState()

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is ParsingHistorySideEffect.OpenParsingResult ->
                    navController.navigate(ImageParsingFeature.Parsing.destination(id = sideEffect.id))
            }
        }

        val appBarState = getAppBarState(
            showClearButton = state.history.isNotEmpty(),
            onClearClick = viewModel::clearHistoryUnconfirmed
        )
        SideEffect {
            scaffoldOptions.appBarConfigure(appBarState)
        }
        ParsingHistoryContent(
            state = state,
            onParsingClick = viewModel::openParsingResult,
            onClearHistory = viewModel::clearHistoryConfirmed,
            onCancelClearing = viewModel::cancelClear
        )
    }

    @Composable
    private fun getAppBarState(
        showClearButton: Boolean,
        onClearClick: () -> Unit
    ) = AppBarState(
        currentRoute = ImageParsingFeature.ParsingHistory.route,
        title = stringResource(Res.string.parsing_history),
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