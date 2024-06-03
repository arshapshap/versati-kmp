package feature.charts.domain.model

data class ChartInfo(
    val id: Long,
    val type: ChartType,
    val xAxisLabels: List<String>,
    val datasets: List<Dataset>,
    val width: Int?,
    val height: Int?,
    val backgroundColor: Int?,
    val imageUrl: String
)