package feature.charts.presentation.chartgeneration.contract

sealed interface ChartGenerationSideEffect {

    data object ShareChart : ChartGenerationSideEffect

    data object NavigateToChartsHistory : ChartGenerationSideEffect

    data object TimeoutError : ChartGenerationSideEffect
}