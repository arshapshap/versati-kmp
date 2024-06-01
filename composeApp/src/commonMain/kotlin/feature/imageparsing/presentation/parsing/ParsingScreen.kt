package feature.imageparsing.presentation.parsing

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
import feature.imageparsing.presentation.parsing.contract.ParsingSideEffect
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.core.parameter.parametersOf
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.ic_history
import versati.composeapp.generated.resources.image_parsing
import versati.composeapp.generated.resources.open_parsing_history

internal object ParsingScreen {

    @Composable
    fun Content(
        navController: NavHostController,
        id: Long?,
        appBarConfigure: (AppBarState) -> Unit
    ) {
        val viewModel =
            getViewModel<ParsingViewModel>(parameters = { parametersOf(id ?: 0) })
        val state by viewModel.collectAsState()

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                // TODO: отображать Toast с ошибкой
//                ParsingSideEffect.AuthorizationError -> context.showToast(R.string.authorization_error)
//                ParsingSideEffect.TimeoutError -> context.showToast(R.string.timeout_error)
//                ParsingSideEffect.NetworkError -> context.showToast(R.string.network_error)
//                ParsingSideEffect.ParsingError -> context.showToast(R.string.parsing_error)
                ParsingSideEffect.NavigateToHistory ->
                    navController.navigate(ImageParsingFeature.ParsingHistory.destination())
                else -> { }
            }
        }

        val appBarState = getAppBarState(
            onHistoryClick = viewModel::navigateToParsingHistory
        )
        SideEffect {
            appBarConfigure(appBarState)
        }
        ParsingContent(
            state = state,
            onUrlChange = viewModel::updateUrl,
            onLanguageChange = viewModel::updateLanguage,
            onParseClick = viewModel::parseImage
        )
    }

    @Composable
    private fun getAppBarState(
        onHistoryClick: () -> Unit = { }
    ) = AppBarState(
        currentRoute = ImageParsingFeature.Parsing.route,
        title = stringResource(Res.string.image_parsing),
        actions = {
            IconButton(onClick = onHistoryClick) {
                Icon(
                    painter = painterResource(Res.drawable.ic_history),
                    contentDescription = stringResource(Res.string.open_parsing_history)
                )
            }
        }
    )
}