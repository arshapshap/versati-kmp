package feature.auth.domain.usecase

import feature.auth.domain.repository.AuthRepository

class LogOutUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke() {
        repository.logOut()
    }
}