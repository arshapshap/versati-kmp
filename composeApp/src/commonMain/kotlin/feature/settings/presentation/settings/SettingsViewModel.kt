package feature.settings.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.auth.domain.usecase.GetCurrentUserUseCase
import feature.auth.domain.usecase.LogOutUseCase
import feature.settings.contract.SettingsSideEffect
import feature.settings.contract.SettingsState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

internal class SettingsViewModel(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val logOutUseCase: LogOutUseCase
) : ContainerHost<SettingsState, SettingsSideEffect>, ViewModel() {

    override val container =
        viewModelScope.container<SettingsState, SettingsSideEffect>(SettingsState())

    init {
        loadData()
    }

    fun logOutUnconfirmed() = intent {
        reduce { state.copy(showDialogToConfirmLogOut = true) }
    }

    fun cancelLogOut() = intent {
        reduce { state.copy(showDialogToConfirmLogOut = false) }
    }

    fun logOutConfirmed() = intent {
        logOutUseCase()
        reduce { state.copy(user = null, showDialogToConfirmLogOut = false) }
    }

    fun navigateToSignIn() = intent {
        postSideEffect(SettingsSideEffect.NavigateToSignIn)
    }

    private fun loadData() = intent {
        reduce { state.copy(userLoading = true) }
        val user = getCurrentUserUseCase()
        reduce { state.copy(user = user, showDialogToConfirmLogOut = false, userLoading = false) }
    }
}