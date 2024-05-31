package feature.qrcodes.domain.usecase

import feature.qrcodes.domain.model.QRCodeInfo
import feature.qrcodes.domain.repository.QRCodesRepository

class CreateQRCodeUseCase(
    private val repository: QRCodesRepository
) {

    suspend operator fun invoke(qrCodeInfo: QRCodeInfo): String {
        return repository.createQRCodeImageUrl(qrCodeInfo)
    }
}