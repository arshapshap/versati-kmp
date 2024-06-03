package feature.auth.presentation.register.contract

import feature.auth.domain.model.RegisterError
import feature.auth.presentation.common.contract.EmailFieldError
import feature.auth.presentation.common.contract.PasswordFieldError

data class RegisterState(
    val email: String = "",
    val password: String = "",
    val loading: Boolean = false,
    val success: Boolean = false,
    val showEmailFieldError: Boolean = false,
    val showPasswordFieldError: Boolean = false,
    val emailFieldError: EmailFieldError? = null,
    val passwordFieldError: PasswordFieldError? = null,
    val registerError: RegisterError? = null,
)