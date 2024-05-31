package feature.charts.presentation.chartshistory.contract

sealed interface ChartsHistorySideEffect {
    data class OpenChart(
        val id: Long
    ) : ChartsHistorySideEffect
}