package feature.qrcodes.presentation.qrcodegeneration.contract

import feature.qrcodes.domain.model.ImageFormat

sealed interface QRCodeGenerationSideEffect {

    data class ShareQRCode(
        val imageFormat: ImageFormat
    ) : QRCodeGenerationSideEffect

    data object NavigateToQRCodesHistory : QRCodeGenerationSideEffect
}