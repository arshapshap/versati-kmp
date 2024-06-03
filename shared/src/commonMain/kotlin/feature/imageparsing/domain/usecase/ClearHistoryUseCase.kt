package feature.imageparsing.domain.usecase

import feature.imageparsing.domain.repository.ImageParsingRepository

class ClearHistoryUseCase(
    private val repository: ImageParsingRepository
) {

    suspend operator fun invoke() {
        return repository.clearHistory()
    }
}