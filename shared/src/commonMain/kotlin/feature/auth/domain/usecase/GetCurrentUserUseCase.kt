package feature.auth.domain.usecase

import feature.auth.domain.model.User
import feature.auth.domain.repository.AuthRepository

class GetCurrentUserUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): User? {
        return repository.getCurrentUser()
    }
}