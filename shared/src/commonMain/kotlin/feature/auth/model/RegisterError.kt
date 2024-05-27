package feature.auth.model

enum class RegisterError {
    EmailAlreadyInUse,
    InvalidEmail,
    WeakPassword,
    UnknownError,
}