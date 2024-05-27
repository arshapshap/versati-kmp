package feature.imageparsing.usecase

import feature.imageparsing.repository.ImageParsingRepository

class ClearHistoryUseCase(
    private val repository: ImageParsingRepository
) {

    suspend operator fun invoke() {
        return repository.clearHistory()
    }
}