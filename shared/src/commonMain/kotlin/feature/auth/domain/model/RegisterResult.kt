package feature.auth.domain.model

data class RegisterResult(
    val isSuccessful: Boolean,
    val error: RegisterError?
)