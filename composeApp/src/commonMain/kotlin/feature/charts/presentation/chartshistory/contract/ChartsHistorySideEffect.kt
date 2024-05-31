package feature.charts.presentation.chartshistory.contract

internal sealed interface ChartsHistorySideEffect {
    data class OpenChart(
        val id: Long
    ) : ChartsHistorySideEffect
}