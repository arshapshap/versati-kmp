package feature.charts.data.repository

import core.database.dao.chartsfeature.ChartDao
import feature.charts.data.mapper.ChartsMapper
import feature.charts.domain.model.ChartInfo
import feature.charts.domain.repository.ChartsRepository

private const val MAX_HISTORY_SIZE = 20

internal class ChartsRepositoryImpl(
    private val dao: ChartDao,
    private val mapper: ChartsMapper,
) : ChartsRepository {

    override suspend fun createChartImageUrl(chartInfo: ChartInfo): String {
        val imageUrl = mapper.createImageUrl(chartInfo)
        dao.add(mapper.mapToLocal(chartInfo, 0, imageUrl))
        if (dao.getCount() > MAX_HISTORY_SIZE)
            dao.deleteOldest()
        return imageUrl
    }

    override suspend fun getChartsHistory(): List<ChartInfo> {
        return dao.getAll().map { mapper.mapFromLocal(it) }
    }

    override suspend fun getChartInfoById(id: Long): ChartInfo? {
        return dao.getById(id)?.let { mapper.mapFromLocal(it) }
    }

    override suspend fun clearHistory() {
        dao.deleteAll()
    }
}