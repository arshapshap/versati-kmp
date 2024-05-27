package feature.charts.usecase

import feature.charts.model.ChartInfo
import feature.charts.repository.ChartsRepository

class CreateChartUseCase(
    private val repository: ChartsRepository
) {

    suspend operator fun invoke(chartInfo: ChartInfo): String {
        return repository.createChartImageUrl(chartInfo)
    }
}