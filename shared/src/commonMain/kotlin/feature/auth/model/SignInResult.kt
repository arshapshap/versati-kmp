package feature.auth.model

data class SignInResult(
    val isSuccessful: Boolean,
    val error: SignInError?
)