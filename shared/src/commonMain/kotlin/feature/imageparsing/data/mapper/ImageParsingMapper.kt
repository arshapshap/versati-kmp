package feature.imageparsing.data.mapper

import core.database.entity.imageparsingfeature.ParsingResultEntity
import feature.imageparsing.data.network.response.ImageParsingResult
import feature.imageparsing.data.network.response.ParsedImageRemote
import feature.imageparsing.domain.model.Language
import feature.imageparsing.domain.model.ParsedImage
import feature.imageparsing.domain.model.ParsingResult
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData

private const val PARSED_TEXT_SEPARATOR = "&%42;"

internal class ImageParsingMapper {

    fun mapToMultiPartForm(url: String, language: Language): MultiPartFormDataContent {
        return MultiPartFormDataContent(
            formData {
                append("url", url)
                append("language", language.code)
                append("isCreateSearchablePdf", "true")
            }
        )
    }

    fun mapFromRemote(remote: ImageParsingResult, id: Long, sourceUrl: String): ParsingResult {
        return ParsingResult(
            id = id,
            parsedResults = remote.parsedResults.map(::mapToParsedImage),
            ocrExitCode = remote.ocrExitCode,
            isErrorOnProcessing = remote.isErrorOnProcessing,
            sourceUrl = sourceUrl,
            searchablePDFURL = remote.searchablePDFURL
        )
    }

    fun mapToLocal(parsingResult: ParsingResult): ParsingResultEntity {
        return ParsingResultEntity(
            id = parsingResult.id,
            parsedText = parsingResult.parsedResults
                .filter { it.fileParseExitCode == 1 }
                .joinToString(PARSED_TEXT_SEPARATOR) {
                    it.parsedText.replace(PARSED_TEXT_SEPARATOR, "")
                },
            sourceUrl = parsingResult.sourceUrl,
            searchablePDFURL = parsingResult.searchablePDFURL
        )
    }

    fun mapFromLocal(local: ParsingResultEntity): ParsingResult {
        return ParsingResult(
            id = local.id,
            parsedResults = local.parsedText.split(PARSED_TEXT_SEPARATOR)
                .map { ParsedImage(parsedText = it) },
            sourceUrl = local.sourceUrl,
            searchablePDFURL = local.searchablePDFURL
        )
    }

    private fun mapToParsedImage(response: ParsedImageRemote): ParsedImage {
        return ParsedImage(
            fileParseExitCode = response.fileParseExitCode,
            parsedText = response.parsedText,
            errorMessage = response.errorMessage,
            errorDetails = response.errorDetails
        )
    }
}