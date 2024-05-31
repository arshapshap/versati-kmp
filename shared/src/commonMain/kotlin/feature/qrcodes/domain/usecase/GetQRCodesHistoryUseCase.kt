package feature.qrcodes.domain.usecase

import feature.qrcodes.domain.model.QRCodeInfo
import feature.qrcodes.domain.repository.QRCodesRepository

class GetQRCodesHistoryUseCase(
    private val repository: QRCodesRepository
) {

    suspend operator fun invoke(): List<QRCodeInfo> {
        return repository.getQRCodesHistory()
    }
}