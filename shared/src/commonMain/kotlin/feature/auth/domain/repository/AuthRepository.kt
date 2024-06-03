package feature.auth.domain.repository

import feature.auth.domain.model.RegisterResult
import feature.auth.domain.model.SignInResult
import feature.auth.domain.model.User

interface AuthRepository {

    suspend fun signIn(email: String, password: String): SignInResult

    suspend fun register(email: String, password: String): RegisterResult

    suspend fun logOut()

    suspend fun getCurrentUser(): User?
}