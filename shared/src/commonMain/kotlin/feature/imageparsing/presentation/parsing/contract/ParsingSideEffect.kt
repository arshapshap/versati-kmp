package feature.imageparsing.presentation.parsing.contract

sealed interface ParsingSideEffect {

    data object AuthorizationError : ParsingSideEffect

    data object TimeoutError : ParsingSideEffect

    data object NetworkError : ParsingSideEffect

    data object ParsingError : ParsingSideEffect

    data object NavigateToHistory : ParsingSideEffect
}