package feature.charts.usecase

import feature.charts.repository.ChartsRepository

class ClearHistoryUseCase(
    private val repository: ChartsRepository
) {

    suspend operator fun invoke() {
        return repository.clearHistory()
    }
}