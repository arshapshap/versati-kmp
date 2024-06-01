package feature.settings.contract

sealed interface SettingsSideEffect {

    data object NavigateToSignIn : SettingsSideEffect
}