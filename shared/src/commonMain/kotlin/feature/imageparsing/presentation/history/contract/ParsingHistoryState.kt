package feature.imageparsing.presentation.history.contract

import feature.imageparsing.domain.model.ParsingResult

data class ParsingHistoryState(
    val history: List<ParsingResult> = listOf(),
    val showDialogToConfirmClear: Boolean = false
)