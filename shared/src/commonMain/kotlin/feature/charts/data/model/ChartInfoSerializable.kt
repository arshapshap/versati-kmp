package feature.charts.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ChartInfoSerializable(
    val type: String,
    val data: DataSerializable
)

@Serializable
data class DataSerializable(
    val labels: List<String>,
    val datasets: List<DatasetSerializable>,
)

@Serializable
data class DatasetSerializable(
    val label: String,
    val data: List<Int>,
    val borderColor: String? = null,
    val borderWidth: Int? = null,
    val fill: Boolean? = null,
    val backgroundColor: String? = null
)