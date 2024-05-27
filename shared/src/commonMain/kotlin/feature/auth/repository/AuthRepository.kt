package feature.auth.repository

import feature.auth.model.RegisterResult
import feature.auth.model.SignInResult
import feature.auth.model.User

interface AuthRepository {

    suspend fun signIn(email: String, password: String): SignInResult

    suspend fun register(email: String, password: String): RegisterResult

    suspend fun logOut()

    suspend fun getCurrentUser(): User?
}