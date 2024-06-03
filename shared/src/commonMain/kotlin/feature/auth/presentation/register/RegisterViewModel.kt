package feature.auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.utils.isEmailValid
import feature.auth.domain.model.RegisterError
import feature.auth.domain.usecase.RegisterUseCase
import feature.auth.presentation.common.contract.EmailFieldError
import feature.auth.presentation.common.contract.PasswordFieldError
import feature.auth.presentation.register.contract.RegisterSideEffect
import feature.auth.presentation.register.contract.RegisterState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.SimpleContext
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

private typealias IntentSyntax = SimpleSyntax<RegisterState, RegisterSideEffect>
private typealias ReduceContext = SimpleContext<RegisterState>

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : ContainerHost<RegisterState, RegisterSideEffect>, ViewModel() {

    override val container =
        viewModelScope.container<RegisterState, RegisterSideEffect>(RegisterState())

    fun register() = intent {
        if (!checkIfEmailAndPasswordValid()) return@intent

        reduce { state.copy(loading = true) }
        val result = registerUseCase(state.email, state.password)
        reduce { state.copy(loading = false) }

        if (result.isSuccessful) {
            reduce { state.copy(success = true) }
            postSideEffect(RegisterSideEffect.NavigateToSettings)
        } else {
            handleRegisterError(result.error!!)
        }
    }

    fun navigateToSignIn() = intent {
        postSideEffect(RegisterSideEffect.NavigateToSignIn)
    }

    @OptIn(OrbitExperimental::class)
    fun updateEmail(email: String) = blockingIntent {
        reduce {
            if (state.registerError == null)
                state.copy(
                    email = email,
                    showEmailFieldError = false,
                    emailFieldError = null
                )
            else
                getStateWithNoErrors()
                    .copy(email = email)
        }
    }

    @OptIn(OrbitExperimental::class)
    fun updatePassword(password: String) = blockingIntent {
        reduce {
            if (state.registerError == null)
                state.copy(
                    password = password,
                    showPasswordFieldError = false,
                    passwordFieldError = null
                )
            else
                getStateWithNoErrors()
                    .copy(password = password)
        }
    }

    private suspend fun IntentSyntax.checkIfEmailAndPasswordValid(): Boolean {
        val emailFieldError = state.email.checkEmail()
        val passwordFieldError = state.password.checkPassword()
        if (emailFieldError != null || passwordFieldError != null) {
            reduce {
                state.copy(
                    showEmailFieldError = emailFieldError != null,
                    showPasswordFieldError = passwordFieldError != null,
                    emailFieldError = emailFieldError,
                    passwordFieldError = passwordFieldError
                )
            }
        }
        return emailFieldError == null && passwordFieldError == null
    }

    private suspend fun IntentSyntax.handleRegisterError(error: RegisterError) {
        when (error) {
            RegisterError.EmailAlreadyInUse -> reduce { getStateWithEmailError(
                EmailFieldError.EmailAlreadyInUse
            ) }
            RegisterError.InvalidEmail -> reduce { getStateWithEmailError(
                EmailFieldError.InvalidEmail
            ) }
            RegisterError.WeakPassword -> reduce { getStateWithPasswordError(
                PasswordFieldError.WeakPassword
            ) }
            RegisterError.UnknownError -> reduce {
                getStateWithError(
                    RegisterError.UnknownError,
                    highlightError = false
                )
            }
        }
    }

    private fun ReduceContext.getStateWithError(
        registerError: RegisterError,
        highlightError: Boolean = true
    ) = state.copy(
        showEmailFieldError = highlightError,
        showPasswordFieldError = highlightError,
        emailFieldError = null,
        passwordFieldError = null,
        registerError = registerError
    )

    private fun ReduceContext.getStateWithEmailError(
        emailFieldError: EmailFieldError
    ) = state.copy(
        showEmailFieldError = true,
        emailFieldError = emailFieldError
    )

    private fun ReduceContext.getStateWithPasswordError(
        passwordFieldError: PasswordFieldError
    ) = state.copy(
        showPasswordFieldError = true,
        passwordFieldError = passwordFieldError
    )

    private fun ReduceContext.getStateWithNoErrors() = state.copy(
        showEmailFieldError = false,
        showPasswordFieldError = false,
        emailFieldError = null,
        passwordFieldError = null,
        registerError = null
    )

    private fun String.checkEmail(): EmailFieldError? {
        if (this.isBlank())
            return EmailFieldError.EmptyEmail
        if (!this.isEmailValid())
            return EmailFieldError.InvalidEmail
        return null
    }

    private fun String.checkPassword(): PasswordFieldError? {
        if (this.isBlank())
            return PasswordFieldError.EmptyPassword
        return null
    }
}