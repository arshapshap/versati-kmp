package feature.charts.presentation.chartgeneration.contract

import io.github.skeptick.libres.images.Image

sealed interface ChartGenerationSideEffect {

    data class ShareChart(
        val image: Image?
    ) : ChartGenerationSideEffect

    data object NavigateToChartsHistory : ChartGenerationSideEffect

    data object TimeoutError : ChartGenerationSideEffect
}