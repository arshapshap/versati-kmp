package core.database.dao.authfeature

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import core.database.entity.authfeature.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun insert(item: UserEntity): Long

    @Query("SELECT * FROM User WHERE email=:email")
    suspend fun findByEmail(email: String): UserEntity?

    @Query("SELECT * FROM User WHERE id=:id")
    suspend fun findById(id: Long): UserEntity?
}