package feature.charts.domain.repository

import feature.charts.domain.model.ChartInfo

interface ChartsRepository {

    suspend fun clearHistory()

    suspend fun createChartImageUrl(chartInfo: ChartInfo): String

    suspend fun getChartInfoById(id: Long): ChartInfo?

    suspend fun getChartsHistory(): List<ChartInfo>
}