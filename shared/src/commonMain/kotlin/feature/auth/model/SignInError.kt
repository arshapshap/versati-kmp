package feature.auth.model

enum class SignInError {
    WrongPassword,
    InvalidEmail,
    UserDisabled,
    UserNotFound,
    UnknownError
}