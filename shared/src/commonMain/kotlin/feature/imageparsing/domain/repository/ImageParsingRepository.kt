package feature.imageparsing.domain.repository

import feature.imageparsing.domain.model.Language
import feature.imageparsing.domain.model.ParsingResult

interface ImageParsingRepository {

    suspend fun parseImageByUrl(url: String, language: Language): ParsingResult

    suspend fun getParsingHistory(): List<ParsingResult>

    suspend fun clearHistory()

    suspend fun getParsingResultById(id: Long): ParsingResult?
}