package feature.charts.domain.usecase

import feature.charts.domain.model.ChartInfo
import feature.charts.domain.repository.ChartsRepository

class GetChartInfoByIdUseCase(
    private val repository: ChartsRepository
) {

    suspend operator fun invoke(id: Long): ChartInfo? {
        return repository.getChartInfoById(id)
    }
}