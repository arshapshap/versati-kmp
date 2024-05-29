package feature.auth.data

import feature.auth.domain.model.RegisterResult
import feature.auth.domain.model.SignInResult
import feature.auth.domain.model.User
import feature.auth.domain.repository.AuthRepository

class AuthRepositoryImpl : AuthRepository {

    override suspend fun signIn(email: String, password: String): SignInResult {
        TODO("Not yet implemented")
    }

    override suspend fun register(email: String, password: String): RegisterResult {
        TODO("Not yet implemented")
    }

    override suspend fun logOut() {
//        TODO("Not yet implemented")
    }

    override suspend fun getCurrentUser(): User? {
        //TODO("Not yet implemented")
        return null
    }
}