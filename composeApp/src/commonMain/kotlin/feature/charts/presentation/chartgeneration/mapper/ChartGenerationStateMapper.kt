package feature.charts.presentation.chartgeneration.mapper

import feature.charts.domain.model.ChartInfo
import feature.charts.domain.model.Dataset
import feature.charts.presentation.chartgeneration.contract.ChartGenerationState
import feature.charts.presentation.chartgeneration.contract.DatasetState

internal class ChartGenerationStateMapper {

    fun toState(chartInfo: ChartInfo) = ChartGenerationState(
        labels = chartInfo.xAxisLabels.joinToString(", "),
        datasets = chartInfo.datasets.map { dataset ->
            DatasetState(
                label = dataset.label,
                data = dataset.data.joinToString(", ")
            )
        },
        chartImageUrl = chartInfo.imageUrl
    )

    fun fromState(state: ChartGenerationState) = ChartInfo(
        id = 0,
        type = state.chartType,
        xAxisLabels = state.labels.split(',').map { it.trim() },
        datasets = state.datasets.map { dataset ->
            Dataset(
                label = dataset.label.trim(),
                data = dataset.data.split(',').map { it.trim().toIntOrNull() ?: 0 },
                borderColor = null,
                borderWidth = null,
                fill = null,
                backgroundColor = null,
            )
        },
        backgroundColor = null,
        width = null,
        height = null,
        imageUrl = ""
    )
}