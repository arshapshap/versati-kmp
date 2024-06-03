package feature.auth.domain.usecase

import feature.auth.domain.model.SignInResult
import feature.auth.domain.repository.AuthRepository

class SignInUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): SignInResult {
        return repository.signIn(email, password)
    }
}