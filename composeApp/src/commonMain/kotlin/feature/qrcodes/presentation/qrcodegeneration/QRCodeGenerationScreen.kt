package feature.qrcodes.presentation.qrcodegeneration

import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import core.navigation.features.QRCodesFeature
import core.navigation.state.AppBarState
import core.presentation_utils.collectAsState
import core.presentation_utils.collectSideEffect
import core.presentation_utils.getViewModel
import feature.qrcodes.presentation.qrcodegeneration.contract.QRCodeGenerationSideEffect
import main.ScaffoldOptions
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.core.parameter.parametersOf
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.ic_history
import versati.composeapp.generated.resources.open_qr_codes_history
import versati.composeapp.generated.resources.qr_codes


internal object QRCodeGenerationScreen {

    @Composable
    fun Content(
        navController: NavHostController,
        id: Long?,
        scaffoldOptions: ScaffoldOptions
    ) {
        val viewModel =
            getViewModel<QRCodeGenerationViewModel>(parameters = { parametersOf(id ?: 0) })
        val state by viewModel.collectAsState()

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is QRCodeGenerationSideEffect.ShareQRCode -> {
                    // TODO: добавить шэринг ?
//                    shareQRCode(
//                        context = context,
//                        bitmap = sideEffect.bitmap,
//                        format = sideEffect.imageFormat.name.lowercase()
//                    )
                }

                QRCodeGenerationSideEffect.NavigateToQRCodesHistory ->
                    navController.navigate(QRCodesFeature.QRCodesHistory.destination())
            }
        }

        val appBarState = getAppBarState(viewModel::navigateToQRCodesHistory)
        SideEffect {
            scaffoldOptions.appBarConfigure(appBarState)
        }
        QRCodeGenerationContent(
            state = state,
            onAdvancedOptionsExpand = viewModel::expandAdvancedOptions,
            onDataChange = viewModel::updateData,
            onSizeChange = viewModel::updateSize,
            onQRCodeColorChange = viewModel::updateColor,
            onBackgroundColorChange = viewModel::updateBackgroundColor,
            onQuietZoneChange = viewModel::updateQuietZone,
            onFormatChange = viewModel::updateFormat,
            onCreateClick = viewModel::createQRCode,
            onShareClick = viewModel::shareQRCode,
            onImageLoadingSuccess = viewModel::onImageLoadingSuccess,
            onImageLoadingError = viewModel::onImageLoadingError,
        )
    }

    @Composable
    private fun getAppBarState(
        onHistoryClick: () -> Unit
    ) = AppBarState(
        currentRoute = QRCodesFeature.QRCodeGeneration.route,
        title = stringResource(Res.string.qr_codes),
        actions = {
            IconButton(onClick = onHistoryClick) {
                Icon(
                    painter = painterResource(Res.drawable.ic_history),
                    contentDescription = stringResource(Res.string.open_qr_codes_history)
                )
            }
        }
    )

//    private fun shareQRCode(context: Context, bitmap: Bitmap?, format: String) {
//        if (bitmap == null) {
//            context.showToast(context.getString(R.string.no_uploaded_image))
//            return
//        }
//        val uri = StorageHelper.getImageUriFromBitmap(
//            context = context,
//            bitmap = bitmap,
//            fileNamePrefix = "QR",
//            format = format
//        )
//        SharingHelper.shareImage(context, uri)
//    }
}