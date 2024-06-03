package feature.qrcodes.domain.usecase

import feature.qrcodes.domain.repository.QRCodesRepository

class ClearHistoryUseCase(
    private val repository: QRCodesRepository
) {

    suspend operator fun invoke() {
        return repository.clearHistory()
    }
}