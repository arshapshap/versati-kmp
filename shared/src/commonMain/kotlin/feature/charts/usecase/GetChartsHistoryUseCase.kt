package feature.charts.usecase

import feature.charts.model.ChartInfo
import feature.charts.repository.ChartsRepository

class GetChartsHistoryUseCase(
    private val repository: ChartsRepository
) {

    suspend operator fun invoke(): List<ChartInfo> {
        return repository.getChartsHistory()
    }
}