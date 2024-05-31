package feature.charts.presentation.chartgeneration.elements

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import core.designsystem.elements.DropdownInput
import feature.charts.domain.model.ChartType
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.chart_type
import versati.composeapp.generated.resources.ic_bar_chart
import versati.composeapp.generated.resources.ic_line_chart
import versati.composeapp.generated.resources.ic_pie_chart
import versati.composeapp.generated.resources.ic_radar_chart

@Composable
internal fun ChartTypeInput(
    modifier: Modifier,
    chartType: ChartType,
    onValueChange: (ChartType) -> Unit
) {
    DropdownInput(
        modifier = modifier,
        valueString = chartType.name,
        onSelect = {
            onValueChange(ChartType.valueOf(it))
        },
        entries = ChartType.entries.map { it.name },
        leadingIcon = {

            Icon(
                painter = getChartIcon(chartType),
                tint = MaterialTheme.colorScheme.outline,
                contentDescription = null
            )
        },
        label = stringResource(Res.string.chart_type)
    )
}

@Composable
private fun getChartIcon(chartType: ChartType): Painter {
    return when (chartType) {
        ChartType.Bar -> painterResource(Res.drawable.ic_bar_chart)
        ChartType.Line -> painterResource(Res.drawable.ic_line_chart)
        ChartType.Pie -> painterResource(Res.drawable.ic_pie_chart)
        ChartType.Radar -> painterResource(Res.drawable.ic_radar_chart)
    }
}

@Preview
@Composable
private fun ChartTypeInputPreview() {
    ChartTypeInput(
        modifier = Modifier,
        chartType = ChartType.Bar,
        onValueChange = { }
    )
}