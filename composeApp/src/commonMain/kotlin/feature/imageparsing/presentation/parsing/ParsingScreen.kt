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
import main.ScaffoldOptions
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.core.parameter.parametersOf
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.authorization_error
import versati.composeapp.generated.resources.ic_history
import versati.composeapp.generated.resources.image_parsing
import versati.composeapp.generated.resources.network_error
import versati.composeapp.generated.resources.open_parsing_history
import versati.composeapp.generated.resources.parsing_error
import versati.composeapp.generated.resources.timeout_error

internal object ParsingScreen {

    @Composable
    fun Content(
        navController: NavHostController,
        id: Long?,
        scaffoldOptions: ScaffoldOptions
    ) {
        val viewModel =
            getViewModel<ParsingViewModel>(parameters = { parametersOf(id ?: 0) })
        val state by viewModel.collectAsState()

        val authorizationErrorString = stringResource(Res.string.authorization_error)
        val timeoutError = stringResource(Res.string.timeout_error)
        val networkError = stringResource(Res.string.network_error)
        val parsingError = stringResource(Res.string.parsing_error)
        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                ParsingSideEffect.AuthorizationError ->
                    scaffoldOptions.snackbarHostState.showSnackbar(authorizationErrorString)
                ParsingSideEffect.TimeoutError ->
                    scaffoldOptions.snackbarHostState.showSnackbar(timeoutError)
                ParsingSideEffect.NetworkError ->
                    scaffoldOptions.snackbarHostState.showSnackbar(networkError)
                ParsingSideEffect.ParsingError ->
                    scaffoldOptions.snackbarHostState.showSnackbar(parsingError)
                ParsingSideEffect.NavigateToHistory ->
                    navController.navigate(ImageParsingFeature.ParsingHistory.destination())
            }
        }

        val appBarState = getAppBarState(
            onHistoryClick = viewModel::navigateToParsingHistory
        )
        SideEffect {
            scaffoldOptions.appBarConfigure(appBarState)
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