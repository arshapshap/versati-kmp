package feature.auth.usecase

import feature.auth.repository.AuthRepository

class LogOutUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke() {
        repository.logOut()
    }
}