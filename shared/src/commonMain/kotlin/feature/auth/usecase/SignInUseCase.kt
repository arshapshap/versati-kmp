package feature.auth.usecase

import feature.auth.model.SignInResult
import feature.auth.repository.AuthRepository

class SignInUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): SignInResult {
        return repository.signIn(email, password)
    }
}