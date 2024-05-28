package feature.auth.presentation.signin.contract

sealed interface SignInSideEffect {

    data object NavigateToRegistration : SignInSideEffect

    data object NavigateToSettings : SignInSideEffect
}