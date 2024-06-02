package core.database.entity.imageparsingfeature

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ParsingResult")
data class ParsingResultEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "parsed_text") val parsedText: String,
    @ColumnInfo(name = "source_url", defaultValue = "") val sourceUrl: String,
    @ColumnInfo(name = "searchable_pdf_url") val searchablePDFURL: String
)