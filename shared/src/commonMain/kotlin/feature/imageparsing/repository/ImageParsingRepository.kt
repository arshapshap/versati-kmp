package feature.imageparsing.repository

import feature.imageparsing.model.Language
import feature.imageparsing.model.ParsingResult

interface ImageParsingRepository {

    suspend fun parseImageByUrl(url: String, language: Language): ParsingResult

    suspend fun getParsingHistory(): List<ParsingResult>

    suspend fun clearHistory()

    suspend fun getParsingResultById(id: Long): ParsingResult?
}