package feature.auth.presentation.common.utils

import androidx.compose.runtime.Composable
import feature.auth.domain.model.RegisterError
import feature.auth.domain.model.SignInError
import feature.auth.presentation.common.contract.EmailFieldError
import feature.auth.presentation.common.contract.PasswordFieldError
import org.jetbrains.compose.resources.stringResource
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.email_already_in_use
import versati.composeapp.generated.resources.enter_email
import versati.composeapp.generated.resources.enter_password
import versati.composeapp.generated.resources.incorrect_email_or_password
import versati.composeapp.generated.resources.invalid_email
import versati.composeapp.generated.resources.unknown_error
import versati.composeapp.generated.resources.user_disabled
import versati.composeapp.generated.resources.user_not_found
import versati.composeapp.generated.resources.weak_password

@Composable
internal fun SignInError.getMessage(): String = when (this) {
    SignInError.WrongPassword -> stringResource(Res.string.incorrect_email_or_password)
    SignInError.InvalidEmail -> stringResource(Res.string.invalid_email)
    SignInError.UserDisabled -> stringResource(Res.string.user_disabled)
    SignInError.UserNotFound -> stringResource(Res.string.user_not_found)
    SignInError.UnknownError -> stringResource(Res.string.unknown_error)
}

@Composable
internal fun RegisterError.getMessage(): String = when (this) {
    RegisterError.EmailAlreadyInUse -> stringResource(Res.string.email_already_in_use)
    RegisterError.InvalidEmail -> stringResource(Res.string.invalid_email)
    RegisterError.WeakPassword -> stringResource(Res.string.weak_password)
    RegisterError.UnknownError -> stringResource(Res.string.unknown_error)
}

@Composable
internal fun EmailFieldError.getMessage(): String = when (this) {
    EmailFieldError.EmptyEmail -> stringResource(Res.string.enter_email)
    EmailFieldError.InvalidEmail -> stringResource(Res.string.invalid_email)
    EmailFieldError.EmailAlreadyInUse -> stringResource(Res.string.email_already_in_use)
}

@Composable
internal fun PasswordFieldError.getMessage(): String = when (this) {
    PasswordFieldError.EmptyPassword -> stringResource(Res.string.enter_password)
    PasswordFieldError.WeakPassword -> stringResource(Res.string.weak_password)
}