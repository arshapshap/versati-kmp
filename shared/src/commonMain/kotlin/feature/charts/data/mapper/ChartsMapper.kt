package feature.charts.data.mapper

import core.database.entity.chartsfeature.ChartEntity
import feature.charts.data.model.ChartInfoSerializable
import feature.charts.data.model.DataSerializable
import feature.charts.data.model.DatasetSerializable
import feature.charts.data.utils.encodeUrl
import feature.charts.data.utils.fromJson
import feature.charts.data.utils.toJson
import feature.charts.domain.model.ChartInfo
import feature.charts.domain.model.ChartType
import feature.charts.domain.model.Dataset
import feature.qrcodes.presentation.utils.toHex

private const val QUICKCHART_BASE_URL = "https://quickchart.io/"

class ChartsMapper {

    fun createImageUrl(chartInfo: ChartInfo): String {
        val url = StringBuilder(QUICKCHART_BASE_URL)
        url.append("chart")
        url.append("?c=${chartInfo.toSerializable().toJson().encodeUrl()}")
        url.append("&encoding=url")
        chartInfo.width?.let { url.append("&w=$it") }
        chartInfo.height?.let { url.append("&h=$it") }
        chartInfo.backgroundColor?.let { url.append("&bkg=#$it") }

        return url.toString()
    }

    fun mapToLocal(chartInfo: ChartInfo, id: Long, imageUrl: String): ChartEntity {
        return ChartEntity(
            id = id,
            type = chartInfo.type.name,
            xAxisLabelsJson = chartInfo.xAxisLabels.toJson(),
            datasetsJson = chartInfo.datasets.toSerializable().toJson(),
            width = chartInfo.width ?: -1,
            height = chartInfo.height ?: -1,
            backgroundColor = chartInfo.backgroundColor ?: -1,
            imageUrl = imageUrl
        )
    }

    fun mapFromLocal(local: ChartEntity): ChartInfo {
        return ChartInfo(
            id = local.id,
            type = ChartType.valueOf(local.type),
            xAxisLabels = local.xAxisLabelsJson.fromJson(),
            datasets = local.datasetsJson.fromJson<List<DatasetSerializable>>().fromSerializable(),
            width = if (local.width >= 0) local.width else null,
            height = if (local.height >= 0) local.height else null,
            backgroundColor = if (local.backgroundColor >= 0) local.backgroundColor else null,
            imageUrl = local.imageUrl
        )
    }

    private fun ChartInfo.toSerializable(): ChartInfoSerializable {
        return ChartInfoSerializable(
            type = type.name.lowercase(),
            data = DataSerializable(
                labels = xAxisLabels,
                datasets = datasets.toSerializable(),
            )
        )
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun List<Dataset>.toSerializable(): List<DatasetSerializable> {
        return this.map { dataset ->
            DatasetSerializable(
                label = dataset.label,
                data = dataset.data,
                borderColor = dataset.borderColor?.let { "#${it.toHex()}" },
                borderWidth = dataset.borderWidth,
                fill = dataset.fill,
                backgroundColor = dataset.backgroundColor?.let { "#${it.toHex()}" }
            )
        }
    }

    private fun List<DatasetSerializable>.fromSerializable(): List<Dataset> {
        return this.map {
            Dataset(
                label = it.label,
                data = it.data,
                borderColor = it.borderColor?.drop(1)?.toInt(16), // drop(1) for dropping "#" symbol
                borderWidth = it.borderWidth,
                fill = it.fill,
                backgroundColor = it.backgroundColor?.drop(1)?.toInt(16)
            )
        }
    }
}