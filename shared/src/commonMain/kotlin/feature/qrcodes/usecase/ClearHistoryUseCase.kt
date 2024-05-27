package feature.qrcodes.usecase

import feature.qrcodes.repository.QRCodesRepository

class ClearHistoryUseCase(
    private val repository: QRCodesRepository
) {

    suspend operator fun invoke() {
        return repository.clearHistory()
    }
}