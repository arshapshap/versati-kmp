package feature.qrcodes.presentation.qrcodegeneration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.qrcodes.domain.model.ImageFormat
import feature.qrcodes.domain.model.QRCodeInfo
import feature.qrcodes.domain.usecase.CreateQRCodeUseCase
import feature.qrcodes.domain.usecase.GetQRCodeInfoByIdUseCase
import feature.qrcodes.presentation.qrcodegeneration.contract.QRCodeGenerationSideEffect
import feature.qrcodes.presentation.qrcodegeneration.contract.QRCodeGenerationState
import feature.qrcodes.presentation.utils.toHex
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import kotlin.math.min

private typealias IntentSyntax = SimpleSyntax<QRCodeGenerationState, QRCodeGenerationSideEffect>

class QRCodeGenerationViewModel(
    qrCodeInfoId: Long,
    private val createQRCodeUseCase: CreateQRCodeUseCase,
    private val getQRCodeInfoByIdUseCase: GetQRCodeInfoByIdUseCase
) : ContainerHost<QRCodeGenerationState, QRCodeGenerationSideEffect>, ViewModel() {

    override val container =
        viewModelScope
            .container<QRCodeGenerationState, QRCodeGenerationSideEffect>(QRCodeGenerationState())

    init {
        if (qrCodeInfoId != 0L)
            loadQRCodeInfo(qrCodeInfoId)
    }

    fun createQRCode() = intent {
        if (state.success && !state.optionsChanged) return@intent

        reduce { state.copy(qrCodeImageUrl = "", success = false) }
        if (!checkIfOptionsValid()) return@intent

        reduce { state.copy(loading = true) }
        val result = createQRCodeUseCase(getQRCodeOptions())
        reduce { state.copy(qrCodeImageUrl = result, optionsChanged = false) }
    }

    fun shareQRCode() = intent {
        postSideEffect(QRCodeGenerationSideEffect.ShareQRCode(state.format))
    }

    fun navigateToQRCodesHistory() = intent {
        postSideEffect(QRCodeGenerationSideEffect.NavigateToQRCodesHistory)
    }

    fun onImageLoadingSuccess() = intent {
        reduce { state.copy(success = true, loading = false) }
    }

    fun onImageLoadingError() = intent {
        reduce { state.copy(success = false, loading = false) }
    }


    @OptIn(OrbitExperimental::class)
    fun expandAdvancedOptions() = blockingIntent {
        reduce { state.copy(advancedOptionsExpanded = !state.advancedOptionsExpanded) }
    }

    @OptIn(OrbitExperimental::class)
    fun updateData(data: String) = blockingIntent {
        reduce { state.copy(data = data, optionsChanged = true) }
    }

    @OptIn(OrbitExperimental::class)
    fun updateSize(size: String) = blockingIntent {
        reduce { state.copy(size = size.toIntOrNull(), optionsChanged = true) }
    }

    @OptIn(OrbitExperimental::class)
    fun updateColor(color: String) = blockingIntent {
        reduce {
            state.copy(
                qrCodeColorString = color,
                qrCodeColor = color.toIntOrNull(16),
                optionsChanged = true
            )
        }
    }

    @OptIn(OrbitExperimental::class)
    fun updateBackgroundColor(color: String) = blockingIntent {
        reduce {
            state.copy(
                backgroundColorString = color,
                backgroundColor = color.toIntOrNull(16),
                optionsChanged = true
            )
        }
    }

    @OptIn(OrbitExperimental::class)
    fun updateQuietZone(quietZone: String) = blockingIntent {
        reduce { state.copy(quietZone = quietZone.toIntOrNull(), optionsChanged = true) }
    }

    @OptIn(OrbitExperimental::class)
    fun updateFormat(format: ImageFormat) = blockingIntent {
        reduce { state.copy(format = format, optionsChanged = true) }
    }

    private fun loadQRCodeInfo(id: Long) = intent {
        val qrCodeInfoById = getQRCodeInfoByIdUseCase(id) ?: return@intent
        reduce {
            state.copy(
                data = qrCodeInfoById.data,
                size = qrCodeInfoById.size,
                qrCodeColor = qrCodeInfoById.color,
                backgroundColor = qrCodeInfoById.backgroundColor,
                quietZone = qrCodeInfoById.quietZone,
                format = qrCodeInfoById.format,
                qrCodeImageUrl = qrCodeInfoById.imageUrl,
                optionsChanged = false
            )
        }
    }

    private suspend fun IntentSyntax.checkIfOptionsValid(): Boolean {
        reduce {
            state.copy(
                size = validateSize(state.size),
                showDataFieldError = state.data.isBlank(),
                qrCodeColorString = state.qrCodeColor?.toHex()
                    ?: state.qrCodeColorString,
                showColorFieldError = state.qrCodeColor == null,
                backgroundColorString = state.backgroundColor?.toHex()
                    ?: state.backgroundColorString,
                showBackgroundColorFieldError = state.backgroundColor == null,
                quietZone = validateQuietZone(state.quietZone)
            )
        }
        return !state.showDataFieldError && !state.showColorFieldError && !state.showBackgroundColorFieldError
    }

    private fun IntentSyntax.getQRCodeOptions() = QRCodeInfo(
        id = 0,
        data = state.data,
        size = state.size ?: 200,
        color = state.qrCodeColor ?: 0x000000,
        backgroundColor = state.backgroundColor ?: 0xFFFFFF,
        quietZone = state.quietZone ?: 0,
        format = state.format,
        imageUrl = ""
    )

    private fun validateSize(size: Int?): Int {
        if (size == null || size < 10)
            return 10
        return min(size, 1000)
    }

    private fun validateQuietZone(quietZone: Int?): Int {
        if (quietZone == null)
            return 0
        return min(quietZone, 100)
    }
}