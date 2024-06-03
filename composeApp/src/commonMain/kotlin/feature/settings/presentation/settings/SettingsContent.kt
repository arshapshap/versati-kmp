package feature.settings.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import feature.auth.domain.model.User
import feature.settings.presentation.contract.SettingsState
import feature.settings.presentation.settings.elements.AccountInfo
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun SettingsContent(
    state: SettingsState,
    onLogInClick: () -> Unit = { },
    onLogOutClick: () -> Unit = { },
    onCancelLogOut: () -> Unit = { },
    onLogOut: () -> Unit = { }
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AccountInfo(
            user = state.user,
            loading = state.userLoading,
            showDialogToConfirmLogOut = state.showDialogToConfirmLogOut,
            onLogInClick = onLogInClick,
            onLogOutClick = onLogOutClick,
            onCancelLogOut = onCancelLogOut,
            onLogOut = onLogOut
        )
        HorizontalDivider()
    }
}

@Preview
@Composable
private fun Preview() {
    val state = SettingsState(
        user = User(id = 1L, email = "example@gmail.com")
    )
    SettingsContent(state = state)
}