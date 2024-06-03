package feature.imageparsing.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.imageparsing.domain.usecase.ClearHistoryUseCase
import feature.imageparsing.domain.usecase.GetParsingHistoryUseCase
import feature.imageparsing.presentation.history.contract.ParsingHistorySideEffect
import feature.imageparsing.presentation.history.contract.ParsingHistoryState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

class ParsingHistoryViewModel(
    private val getParsingHistoryUseCase: GetParsingHistoryUseCase,
    private val clearHistoryUseCase: ClearHistoryUseCase
) : ContainerHost<ParsingHistoryState, ParsingHistorySideEffect>, ViewModel() {

    override val container =
        viewModelScope
            .container<ParsingHistoryState, ParsingHistorySideEffect>(ParsingHistoryState())

    init {
        loadHistory()
    }

    fun openParsingResult(id: Long) = intent {
        postSideEffect(ParsingHistorySideEffect.OpenParsingResult(id))
    }

    fun clearHistoryUnconfirmed() = intent {
        reduce { state.copy(showDialogToConfirmClear = true) }
    }

    fun cancelClear() = intent {
        reduce { state.copy(showDialogToConfirmClear = false) }
    }

    fun clearHistoryConfirmed() = intent {
        clearHistoryUseCase()
        reduce { state.copy(history = listOf(), showDialogToConfirmClear = false) }
    }

    private fun loadHistory() = intent {
        val list = getParsingHistoryUseCase().asReversed()
        reduce { state.copy(history = list) }
    }
}