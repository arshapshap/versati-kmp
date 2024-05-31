package feature.charts.presentation.chartshistory.contract

import androidx.compose.runtime.Immutable
import feature.charts.domain.model.ChartInfo

@Immutable
internal data class ChartsHistoryState(
    val history: List<ChartInfo> = listOf(),
    val showDialogToConfirmClear: Boolean = false
)