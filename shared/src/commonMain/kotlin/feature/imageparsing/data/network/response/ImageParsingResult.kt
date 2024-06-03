package feature.imageparsing.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ImageParsingResult(
    @SerialName("ParsedResults")
    val parsedResults: List<ParsedImageRemote> = listOf(),
    @SerialName("OCRExitCode")
    val ocrExitCode: Int,
    @SerialName("IsErroredOnProcessing")
    val isErrorOnProcessing: Boolean,
    @SerialName("SearchablePDFURL")
    val searchablePDFURL: String = ""
)