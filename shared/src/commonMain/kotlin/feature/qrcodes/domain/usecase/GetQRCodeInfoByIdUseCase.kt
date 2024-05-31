package feature.qrcodes.domain.usecase

import feature.qrcodes.domain.model.QRCodeInfo
import feature.qrcodes.domain.repository.QRCodesRepository

class GetQRCodeInfoByIdUseCase(
    private val repository: QRCodesRepository
) {

    suspend operator fun invoke(id: Long): QRCodeInfo? {
        return repository.getQRCodeInfoById(id)
    }
}