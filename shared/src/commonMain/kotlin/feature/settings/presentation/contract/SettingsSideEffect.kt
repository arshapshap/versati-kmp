package feature.settings.presentation.contract

sealed interface SettingsSideEffect {

    data object NavigateToSignIn : SettingsSideEffect
}