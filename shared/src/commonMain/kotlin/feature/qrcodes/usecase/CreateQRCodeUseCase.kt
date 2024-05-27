package feature.qrcodes.usecase

import feature.qrcodes.model.QRCodeInfo
import feature.qrcodes.repository.QRCodesRepository

class CreateQRCodeUseCase(
    private val repository: QRCodesRepository
) {

    suspend operator fun invoke(qrCodeInfo: QRCodeInfo): String {
        return repository.createQRCodeImageUrl(qrCodeInfo)
    }
}