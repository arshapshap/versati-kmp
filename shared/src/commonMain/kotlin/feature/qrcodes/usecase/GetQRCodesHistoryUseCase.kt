package feature.qrcodes.usecase

import feature.qrcodes.model.QRCodeInfo
import feature.qrcodes.repository.QRCodesRepository

class GetQRCodesHistoryUseCase(
    private val repository: QRCodesRepository
) {

    suspend operator fun invoke(): List<QRCodeInfo> {
        return repository.getQRCodesHistory()
    }
}