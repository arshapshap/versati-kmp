package feature.charts.presentation.chartgeneration

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
import feature.charts.presentation.chartgeneration.contract.ChartGenerationSideEffect
import main.ScaffoldOptions
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.core.parameter.parametersOf
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.charts
import versati.composeapp.generated.resources.ic_history
import versati.composeapp.generated.resources.open_charts_history
import versati.composeapp.generated.resources.timeout_error


internal object ChartGenerationScreen {

    @Composable
    fun Content(
        navController: NavHostController,
        id: Long?,
        scaffoldOptions: ScaffoldOptions
    ) {
        val viewModel =
            getViewModel<ChartGenerationViewModel>(parameters = { parametersOf(id ?: 0) })
        val state by viewModel.collectAsState()

        val timeoutErrorString = stringResource(Res.string.timeout_error)
        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is ChartGenerationSideEffect.ShareChart -> {
                    // TODO: добавить шэринг ?
//                    shareQRCode(
//                        context = context,
//                        bitmap = sideEffect.bitmap
//                    )
                }
                ChartGenerationSideEffect.NavigateToChartsHistory -> {
                    navController.navigate(ChartsFeature.ChartsHistory.destination())
                }
                ChartGenerationSideEffect.TimeoutError -> {
                    scaffoldOptions.snackbarHostState.showSnackbar(timeoutErrorString)
                }
            }
        }

        val appBarState = getAppBarState(viewModel::navigateToChartsHistory)
        SideEffect {
            scaffoldOptions.appBarConfigure(appBarState)
        }
        ChartGenerationContent(
            state = state,
            onLabelsChange = viewModel::updateLabels,
            onChartTypeChange = viewModel::updateChartType,
            onDatasetExpand = viewModel::expandDataset,
            onDatasetDelete = viewModel::deleteDataset,
            onDatasetCreate = viewModel::createDataset,
            onDatasetLabelChange = viewModel::updateDatasetLabel,
            onDatasetChange = viewModel::updateDataset,
            onCreateClick = viewModel::createChart,
            onShareClick = viewModel::shareChart,
            onImageLoadingSuccess = viewModel::onImageLoadingSuccess,
            onImageLoadingError = viewModel::onImageLoadingError
        )
    }

    @Composable
    private fun getAppBarState(
        onHistoryClick: () -> Unit
    ) = AppBarState(
        currentRoute = ChartsFeature.ChartGeneration.route,
        title = stringResource(Res.string.charts),
        actions = {
            IconButton(onClick = onHistoryClick) {
                Icon(
                    painter = painterResource(Res.drawable.ic_history),
                    contentDescription = stringResource(Res.string.open_charts_history)
                )
            }
        }
    )

//    private fun shareQRCode(context: Context, bitmap: Bitmap?) {
//        if (bitmap == null) {
//            context.showToast(R.string.no_uploaded_image)
//            return
//        }
//        val uri = StorageHelper.getImageUriFromBitmap(
//            context = context,
//            fileNamePrefix = "CHART",
//            bitmap = bitmap
//        )
//        SharingHelper.shareImage(context, uri)
//    }
}