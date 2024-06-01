package feature.imageparsing.presentation.parsing.contract

import feature.imageparsing.domain.model.Language
import feature.imageparsing.domain.model.ParsingResult

data class ParsingState(
    val url: String = "",
    val showUrlFieldError: Boolean = false,
    val language: Language = Language.English,
    val parsingResult: ParsingResult? = null,
    val loading: Boolean = false
)