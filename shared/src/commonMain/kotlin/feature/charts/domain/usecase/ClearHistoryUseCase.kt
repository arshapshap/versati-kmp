package feature.charts.domain.usecase

import feature.charts.domain.repository.ChartsRepository

class ClearHistoryUseCase(
    private val repository: ChartsRepository
) {

    suspend operator fun invoke() {
        return repository.clearHistory()
    }
}