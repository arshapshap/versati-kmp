package feature.qrcodes.presentation.qrcodeshistory.contract

sealed interface QRCodesHistorySideEffect {
    data class OpenQRCode(
        val id: Long
    ) : QRCodesHistorySideEffect
}