package feature.charts.domain.usecase

import feature.charts.domain.model.ChartInfo
import feature.charts.domain.repository.ChartsRepository

class CreateChartUseCase(
    private val repository: ChartsRepository
) {

    suspend operator fun invoke(chartInfo: ChartInfo): String {
        return repository.createChartImageUrl(chartInfo)
    }
}