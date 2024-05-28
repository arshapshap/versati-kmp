package feature.auth.domain.model

enum class RegisterError {
    EmailAlreadyInUse,
    InvalidEmail,
    WeakPassword,
    UnknownError,
}