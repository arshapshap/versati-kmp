package feature.charts.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class ChartInfoSerializable(
    val type: String,
    val data: DataSerializable
)

@Serializable
internal data class DataSerializable(
    val labels: List<String>,
    val datasets: List<DatasetSerializable>,
)

@Serializable
internal data class DatasetSerializable(
    val label: String,
    val data: List<Int>,
    val borderColor: String? = null,
    val borderWidth: Int? = null,
    val fill: Boolean? = null,
    val backgroundColor: String? = null
)