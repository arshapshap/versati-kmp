package feature.qrcodes.usecase

import feature.qrcodes.model.QRCodeInfo
import feature.qrcodes.repository.QRCodesRepository

class GetQRCodeInfoByIdUseCase(
    private val repository: QRCodesRepository
) {

    suspend operator fun invoke(id: Long): QRCodeInfo? {
        return repository.getQRCodeInfoById(id)
    }
}