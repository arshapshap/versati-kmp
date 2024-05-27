package feature.charts.usecase

import feature.charts.model.ChartInfo
import feature.charts.repository.ChartsRepository

class GetChartInfoByIdUseCase(
    private val repository: ChartsRepository
) {

    suspend operator fun invoke(id: Long): ChartInfo? {
        return repository.getChartInfoById(id)
    }
}