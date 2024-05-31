package feature.qrcodes.presentation.qrcodegeneration.contract

import feature.qrcodes.domain.model.ImageFormat
import io.github.skeptick.libres.images.Image

sealed interface QRCodeGenerationSideEffect {
    data class ShareQRCode(
        val image: Image?,
        val imageFormat: ImageFormat
    ) : QRCodeGenerationSideEffect

    data object NavigateToQRCodesHistory : QRCodeGenerationSideEffect
}