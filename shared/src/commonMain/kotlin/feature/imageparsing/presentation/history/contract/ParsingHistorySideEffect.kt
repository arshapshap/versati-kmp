package feature.imageparsing.presentation.history.contract

sealed interface ParsingHistorySideEffect {
    data class OpenParsingResult(
        val id: Long
    ) : ParsingHistorySideEffect
}