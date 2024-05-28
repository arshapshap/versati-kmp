package feature.auth.presentation.signin.contract

import feature.auth.domain.model.SignInError
import feature.auth.presentation.common.contract.EmailFieldError
import feature.auth.presentation.common.contract.PasswordFieldError

data class SignInState(
    val email: String = "",
    val password: String = "",
    val loading: Boolean = false,
    val success: Boolean = false,
    val showEmailFieldError: Boolean = false,
    val showPasswordFieldError: Boolean = false,
    val emailFieldError: EmailFieldError? = null,
    val passwordFieldError: PasswordFieldError? = null,
    val signInError: SignInError? = null,
)