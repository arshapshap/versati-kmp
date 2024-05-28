package feature.auth.presentation.register.contract

sealed interface RegisterSideEffect {

    data object NavigateToSignIn : RegisterSideEffect

    data object NavigateToSettings : RegisterSideEffect
}