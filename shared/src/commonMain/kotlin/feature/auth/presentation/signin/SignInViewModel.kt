package feature.auth.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.utils.isEmailValid
import feature.auth.domain.model.SignInError
import feature.auth.domain.usecase.SignInUseCase
import feature.auth.presentation.common.contract.EmailFieldError
import feature.auth.presentation.common.contract.PasswordFieldError
import feature.auth.presentation.signin.contract.SignInSideEffect
import feature.auth.presentation.signin.contract.SignInState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.SimpleContext
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

private typealias IntentSyntax = SimpleSyntax<SignInState, SignInSideEffect>
private typealias ReduceContext = SimpleContext<SignInState>

class SignInViewModel(
    private val signInUseCase: SignInUseCase
) : ContainerHost<SignInState, SignInSideEffect>, ViewModel() {

    override val container = viewModelScope.container<SignInState, SignInSideEffect>(SignInState())

    fun signIn() = intent {
        if (!checkIfEmailAndPasswordValid()) return@intent

        reduce { state.copy(loading = true) }
        val result = signInUseCase(state.email, state.password)
        reduce { state.copy(loading = false) }

        if (result.isSuccessful) {
            reduce { state.copy(success = true) }
            postSideEffect(SignInSideEffect.NavigateToSettings)
        } else {
            handleSignInError(result.error!!)
        }
    }

    fun navigateToRegistration() = intent {
        postSideEffect(SignInSideEffect.NavigateToRegistration)
    }

    @OptIn(OrbitExperimental::class)
    fun updateEmail(email: String) = blockingIntent {
        reduce {
            if (state.signInError == null)
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
            if (state.signInError == null)
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

    private suspend fun IntentSyntax.handleSignInError(error: SignInError) {
        when (error) {
            SignInError.WrongPassword -> reduce { getStateWithError(error) }
            SignInError.InvalidEmail -> reduce { getStateWithEmailError(
                EmailFieldError.InvalidEmail
            ) }
            SignInError.UserDisabled -> reduce { getStateWithError(SignInError.UserDisabled) }
            SignInError.UserNotFound -> reduce { getStateWithError(SignInError.UserNotFound) }
            SignInError.UnknownError -> reduce {
                getStateWithError(
                    SignInError.UnknownError,
                    highlightError = false
                )
            }
        }
    }

    private fun ReduceContext.getStateWithError(
        signInError: SignInError,
        highlightError: Boolean = true
    ) = state.copy(
        showEmailFieldError = highlightError,
        showPasswordFieldError = highlightError,
        emailFieldError = null,
        passwordFieldError = null,
        signInError = signInError
    )

    private fun ReduceContext.getStateWithEmailError(
        emailFieldError: EmailFieldError
    ) = state.copy(
        showEmailFieldError = true,
        emailFieldError = emailFieldError
    )

    private fun ReduceContext.getStateWithNoErrors() = state.copy(
        showEmailFieldError = false,
        showPasswordFieldError = false,
        emailFieldError = null,
        passwordFieldError = null,
        signInError = null
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