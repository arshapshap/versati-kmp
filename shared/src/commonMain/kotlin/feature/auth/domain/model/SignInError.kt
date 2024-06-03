package feature.auth.domain.model

enum class SignInError {
    WrongPassword,
    InvalidEmail,
    UserDisabled,
    UserNotFound,
    UnknownError
}