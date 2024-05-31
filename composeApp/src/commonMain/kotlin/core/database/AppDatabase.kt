package core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import core.database.dao.UserDao
import core.database.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}

