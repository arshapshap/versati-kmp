package feature.charts.presentation.chartgeneration.contract

import feature.charts.domain.model.ChartType
import io.github.skeptick.libres.images.Image

data class ChartGenerationState(
    val labels: String = "",
    val showLabelsInputError: Boolean = false,
    val datasets: List<DatasetState> = listOf(DatasetState()),
    val chartType: ChartType = ChartType.Bar,
    val expandedDataset: Int = 0,
    val chartImageUrl: String = "",
    val loading: Boolean = false,
    val success: Boolean = false,
    val image: Image? = null,
    val optionsChanged: Boolean = true,
    val loadingNumber: Int = 0
)

data class DatasetState(
    val label: String = "",
    val showLabelInputError: Boolean = false,
    val data: String = "",
    val showDataInputError: Boolean = false
)