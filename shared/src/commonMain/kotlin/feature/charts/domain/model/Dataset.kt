package feature.charts.domain.model

data class Dataset(
    val label: String,
    val data: List<Int>,
    val borderColor: Int?,
    val borderWidth: Int?,
    val fill: Boolean?,
    val backgroundColor: Int?
)