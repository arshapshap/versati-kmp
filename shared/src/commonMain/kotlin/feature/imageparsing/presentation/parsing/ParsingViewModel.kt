package feature.imageparsing.presentation.parsing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.network.exception.AuthorizationException
import core.network.exception.NetworkException
import feature.imageparsing.domain.model.Language
import feature.imageparsing.domain.usecase.GetParsingResultByIdUseCase
import feature.imageparsing.domain.usecase.ParseImageByUrlUseCase
import feature.imageparsing.presentation.parsing.contract.ParsingSideEffect
import feature.imageparsing.presentation.parsing.contract.ParsingState
import io.ktor.client.network.sockets.SocketTimeoutException
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

class ParsingViewModel(
    parsingResultId: Long,
    private val parseImageByUrlUseCase: ParseImageByUrlUseCase,
    private val getParsingResultByIdUseCase: GetParsingResultByIdUseCase
) : ContainerHost<ParsingState, ParsingSideEffect>, ViewModel() {

    override val container =
        viewModelScope
            .container<ParsingState, ParsingSideEffect>(ParsingState())

    init {
        if (parsingResultId != 0L)
            loadParsingResult(parsingResultId)
    }

    fun parseImage() = intent {
        if (state.url.isEmpty()) {
            reduce { state.copy(showUrlFieldError = true) }
            return@intent
        }
        try {
            reduce { state.copy(parsingResult = null, loading = true) }
            val result = parseImageByUrlUseCase(state.url, state.language)

            if (result.ocrExitCode != 1) {
                reduce { state.copy(loading = false) }
                postSideEffect(ParsingSideEffect.ParsingError)
            } else {
                reduce { state.copy(parsingResult = result, loading = false) }
            }
        } catch (e: Exception) {
            reduce { state.copy(loading = false) }
            when (e) {
                is NetworkException -> postSideEffect(ParsingSideEffect.NetworkError)
                is SocketTimeoutException -> postSideEffect(ParsingSideEffect.TimeoutError)
                is AuthorizationException -> postSideEffect(ParsingSideEffect.AuthorizationError)
            }
        }
    }

    @OptIn(OrbitExperimental::class)
    fun updateUrl(url: String) = blockingIntent {
        reduce { state.copy(url = url, showUrlFieldError = false) }
    }

    @OptIn(OrbitExperimental::class)
    fun updateLanguage(language: Language) = blockingIntent {
        reduce { state.copy(language = language) }
    }

    fun navigateToParsingHistory() = intent {
        postSideEffect(ParsingSideEffect.NavigateToHistory)
    }

    private fun loadParsingResult(id: Long) = intent {
        val parsingResult = getParsingResultByIdUseCase(id) ?: return@intent
        reduce {
            state.copy(
                parsingResult = parsingResult,
                url = parsingResult.sourceUrl
            )
        }
    }
}