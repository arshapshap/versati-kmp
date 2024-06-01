package feature.settings.contract

import feature.auth.domain.model.User

data class SettingsState(
    val user: User? = null,
    val userLoading: Boolean = false,
    val showDialogToConfirmLogOut: Boolean = false
)