package feature.charts.presentation.chartshistory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import core.designsystem.elements.ConfirmationAlertDialog
import feature.charts.domain.model.ChartInfo
import feature.charts.domain.model.ChartType
import feature.charts.domain.model.Dataset
import feature.charts.presentation.chartshistory.contract.ChartsHistoryState
import feature.charts.presentation.common.ui.ChartImage
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.chart_description_datasets
import versati.composeapp.generated.resources.chart_description_labels
import versati.composeapp.generated.resources.chart_description_type
import versati.composeapp.generated.resources.charts_history_empty
import versati.composeapp.generated.resources.clearing_the_history
import versati.composeapp.generated.resources.do_you_really_want_to_clear_the_history

@Composable
internal fun ChartsHistoryContent(
    state: ChartsHistoryState,
    onChartClick: (Long) -> Unit = { },
    onClearHistory: () -> Unit = { },
    onCancelClearing: () -> Unit = { }
) {
    if (state.showDialogToConfirmClear) {
        ConfirmationAlertDialog(
            onDismissRequest = { onCancelClearing() },
            onConfirmation = { onClearHistory() },
            dialogTitle = stringResource(Res.string.clearing_the_history),
            dialogText = stringResource(Res.string.do_you_really_want_to_clear_the_history),
            icon = Icons.Default.Delete
        )
    }
    if (state.history.isEmpty()) {
        Text(
            text = stringResource(Res.string.charts_history_empty),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
    } else {
        LazyColumn {
            items(state.history) {
                ChartInfo(
                    chartInfo = it,
                    onClick = onChartClick
                )
            }
        }
    }
}

@Composable
private fun ChartInfo(
    chartInfo: ChartInfo,
    onClick: (Long) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick(chartInfo.id) }
        .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .height(70.dp)
        ) {
            ChartInfoText(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(end = 16.dp),
                chartInfo = chartInfo
            )
            ChartImage(
                modifier = Modifier
                    .fillMaxHeight(),
                imageUrl = chartInfo.imageUrl
            )
        }
    }
}

@Composable
private fun ChartInfoText(
    modifier: Modifier,
    chartInfo: ChartInfo
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = getChartLabelsString(chartInfo),
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1
        )
        Text(
            text = getChartDatasetsString(chartInfo),
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1
        )
        Text(
            text = getChartTypeString(chartInfo),
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1
        )
    }
}

@Composable
private fun getChartLabelsString(chartInfo: ChartInfo): String {
    return stringResource(
        Res.string.chart_description_labels,
        chartInfo.xAxisLabels.joinToString(", ")
    )
}

@Composable
private fun getChartDatasetsString(chartInfo: ChartInfo): String {
    return stringResource(
        Res.string.chart_description_datasets,
        chartInfo.datasets.joinToString(", ") { it.label }
    )
}

@Composable
private fun getChartTypeString(chartInfo: ChartInfo): String {
    return stringResource(
        Res.string.chart_description_type,
        chartInfo.type
    )
}

@Preview
@Composable
private fun Preview() {
    val state = ChartsHistoryState(
        List(5) {
            ChartInfo(
                id = 1,
                type = ChartType.Bar,
                xAxisLabels = "January,February,March,April,May".split(','),
                datasets = listOf(
                    Dataset(
                        label = "Users 1",
                        data = listOf(30, 60, 90, 120, 150),
                        borderColor = null,
                        borderWidth = null,
                        fill = null,
                        backgroundColor = null
                    ),
                    Dataset(
                        label = "Users 2",
                        data = listOf(40, 60, 80, 100, 120),
                        borderColor = null,
                        borderWidth = null,
                        fill = null,
                        backgroundColor = null
                    )
                ),
                width = null,
                height = null,
                backgroundColor = null,
                imageUrl = ""
            )
        }
    )
    ChartsHistoryContent(
        state = state,
        onChartClick = { }
    )
}

@Preview
@Composable
private fun HistoryClearPreview() {
    val state = ChartsHistoryState(
        listOf()
    )
    ChartsHistoryContent(
        state = state,
        onChartClick = { }
    )
}