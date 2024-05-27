package feature.auth.model

data class RegisterResult(
    val isSuccessful: Boolean,
    val error: RegisterError?
)