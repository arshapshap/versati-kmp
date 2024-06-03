package feature.charts.presentation.chartshistory.contract

import feature.charts.domain.model.ChartInfo

data class ChartsHistoryState(
    val history: List<ChartInfo> = listOf(),
    val showDialogToConfirmClear: Boolean = false
)