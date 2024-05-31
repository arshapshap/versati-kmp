package feature.auth.data.mapper

import core.database.entity.authfeature.UserEntity
import feature.auth.domain.model.User

class AuthMapper {

    fun mapToEntity(email: String, password: String): UserEntity = UserEntity(
        id = 0L,
        email = email,
        password = password
    )

    fun mapToDomain(entity: UserEntity): User = User(
        id = entity.id,
        email = entity.email
    )
}