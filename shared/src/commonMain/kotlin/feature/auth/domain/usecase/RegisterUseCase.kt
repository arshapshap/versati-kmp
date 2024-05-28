package feature.auth.domain.usecase

import feature.auth.domain.model.RegisterResult
import feature.auth.domain.repository.AuthRepository

class RegisterUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): RegisterResult {
        return repository.register(email, password)
    }
}