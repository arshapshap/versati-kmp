package feature.auth.domain.model

data class SignInResult(
    val isSuccessful: Boolean,
    val error: SignInError?
)