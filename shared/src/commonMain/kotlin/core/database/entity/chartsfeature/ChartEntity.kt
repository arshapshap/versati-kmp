package core.database.entity.chartsfeature

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Chart")
data class ChartEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "labels") val xAxisLabelsJson: String,
    @ColumnInfo(name = "datasets") val datasetsJson: String,
    @ColumnInfo(name = "width") val width: Int,
    @ColumnInfo(name = "height") val height: Int,
    @ColumnInfo(name = "background_color") val backgroundColor: Int,
    @ColumnInfo(name = "image_url") val imageUrl: String
)