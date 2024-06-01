package feature.settings.presentation.settings.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import core.designsystem.elements.ButtonWithIcon
import core.designsystem.elements.ConfirmationAlertDialog
import core.designsystem.elements.OutlinedButtonWithIcon
import feature.auth.domain.model.User
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.are_you_sure_you_want_to_log_out_of_your_account
import versati.composeapp.generated.resources.ic_log_in
import versati.composeapp.generated.resources.ic_log_out
import versati.composeapp.generated.resources.log_in
import versati.composeapp.generated.resources.log_out
import versati.composeapp.generated.resources.you_are_logged_in
import versati.composeapp.generated.resources.you_are_not_logged_in

@Composable
internal fun AccountInfo(
    modifier: Modifier = Modifier,
    user: User?,
    showDialogToConfirmLogOut: Boolean,
    loading: Boolean,
    onLogInClick: () -> Unit,
    onLogOutClick: () -> Unit,
    onCancelLogOut: () -> Unit,
    onLogOut: () -> Unit
) {
    if (showDialogToConfirmLogOut) {
        ConfirmationAlertDialog(
            onDismissRequest = onCancelLogOut,
            onConfirmation = onLogOut,
            dialogTitle = stringResource(Res.string.log_out),
            dialogText = stringResource(Res.string.are_you_sure_you_want_to_log_out_of_your_account),
            icon = painterResource(Res.drawable.ic_log_out)
        )
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (loading) {
                CircularProgressIndicator()
            } else if (user != null)
                AuthorizedUserInfo(user = user, onLogOutClick = onLogOutClick)
            else
                UnauthorizedUserInfo(onLogInClick = onLogInClick)
        }
    }
}

@Composable
private fun AuthorizedUserInfo(
    user: User,
    onLogOutClick: () -> Unit
) {
    Text(
        text = stringResource(Res.string.you_are_logged_in),
        textAlign = TextAlign.Center
    )
    Text(
        text = user.email,
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.padding(8.dp))
    OutlinedButtonWithIcon(
        modifier = Modifier.fillMaxWidth(),
        onClick = onLogOutClick,
        text = stringResource(Res.string.log_out),
        painter = painterResource(Res.drawable.ic_log_out)
    )
}

@Composable
private fun UnauthorizedUserInfo(
    onLogInClick: () -> Unit
) {
    Text(
        text = stringResource(Res.string.you_are_not_logged_in),
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.padding(8.dp))
    ButtonWithIcon(
        modifier = Modifier.fillMaxWidth(),
        onClick = onLogInClick,
        text = stringResource(Res.string.log_in),
        painter = painterResource(Res.drawable.ic_log_in),
        textStyle = MaterialTheme.typography.headlineSmall,
        textFontWeight = FontWeight.Bold
    )
}

@Preview
@Composable
private fun AuthorizedPreview() {
    AccountInfo(
        modifier = Modifier.height(170.dp),
        user = User(id = 1L, email = "example@gmail.com"),
        showDialogToConfirmLogOut = false,
        loading = false,
        onLogInClick = { },
        onLogOutClick = { },
        onCancelLogOut = { },
        onLogOut = { },
    )
}

@Preview
@Composable
private fun NotAuthorizedPreview() {
    AccountInfo(
        modifier = Modifier.height(170.dp),
        user = null,
        showDialogToConfirmLogOut = false,
        loading = false,
        onLogInClick = { },
        onLogOutClick = { },
        onCancelLogOut = { },
        onLogOut = { },
    )
}

@Preview
@Composable
private fun LoadingPreview() {
    AccountInfo(
        modifier = Modifier.height(170.dp),
        user = null,
        showDialogToConfirmLogOut = false,
        loading = true,
        onLogInClick = { },
        onLogOutClick = { },
        onCancelLogOut = { },
        onLogOut = { },
    )
}