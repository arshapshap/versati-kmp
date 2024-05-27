package feature.auth.usecase

import feature.auth.model.RegisterResult
import feature.auth.repository.AuthRepository

class RegisterUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): RegisterResult {
        return repository.register(email, password)
    }
}