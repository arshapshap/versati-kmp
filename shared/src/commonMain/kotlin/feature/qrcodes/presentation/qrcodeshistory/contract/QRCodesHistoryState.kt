package feature.qrcodes.presentation.qrcodeshistory.contract

import feature.qrcodes.domain.model.QRCodeInfo

data class QRCodesHistoryState(
    val history: List<QRCodeInfo> = listOf(),
    val showDialogToConfirmClear: Boolean = false
)