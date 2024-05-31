package feature.charts.domain.usecase

import feature.charts.domain.model.ChartInfo
import feature.charts.domain.repository.ChartsRepository

class GetChartsHistoryUseCase(
    private val repository: ChartsRepository
) {

    suspend operator fun invoke(): List<ChartInfo> {
        return repository.getChartsHistory()
    }
}