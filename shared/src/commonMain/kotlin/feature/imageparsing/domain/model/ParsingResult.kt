package feature.imageparsing.domain.model

data class ParsingResult(
    val id: Long,
    val parsedResults: List<ParsedImage>,
    val ocrExitCode: Int = 1,
    val isErrorOnProcessing: Boolean = false,
    val sourceUrl: String,
    val searchablePDFURL: String
)