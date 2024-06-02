package feature.auth.data.repository

import core.database.dao.authfeature.UserDao
import core.utils.encrypt
import feature.auth.data.helper.AuthSettingsHelper
import feature.auth.data.mapper.AuthMapper
import feature.auth.domain.model.RegisterError
import feature.auth.domain.model.RegisterResult
import feature.auth.domain.model.SignInError
import feature.auth.domain.model.SignInResult
import feature.auth.domain.model.User
import feature.auth.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val dao: UserDao,
    private val mapper: AuthMapper,
    private val authHelper: AuthSettingsHelper
) : AuthRepository {

    override suspend fun signIn(email: String, password: String): SignInResult {
        val foundUser = dao.findByEmail(email)
            ?: return SignInResult(
                isSuccessful = false,
                error = SignInError.WrongPassword
            )
        if (foundUser.password != password.encrypt()) {
            return SignInResult(
                isSuccessful = false,
                error = SignInError.WrongPassword
            )
        }
        authHelper.logIn(foundUser.id)
        return SignInResult(
            isSuccessful = true,
            error = null
        )
    }

    override suspend fun register(email: String, password: String): RegisterResult {
        if (dao.findByEmail(email) != null) {
            return RegisterResult(
                isSuccessful = false,
                error = RegisterError.EmailAlreadyInUse
            )
        }
        val entity = mapper.mapToEntity(email, password.encrypt())
        val userId = dao.insert(entity)
        authHelper.logIn(userId)
        return RegisterResult(
            isSuccessful = true,
            error = null
        )
    }

    override suspend fun logOut() {
        authHelper.logOut()
    }

    override suspend fun getCurrentUser(): User? {
        val userId = authHelper.getCurrentUserId() ?: return null
        val foundUser = dao.findById(userId) ?: return null
        return mapper.mapToDomain(foundUser)
    }
}