package feature.auth.usecase

import feature.auth.model.User
import feature.auth.repository.AuthRepository

class GetCurrentUserUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): User? {
        return repository.getCurrentUser()
    }
}