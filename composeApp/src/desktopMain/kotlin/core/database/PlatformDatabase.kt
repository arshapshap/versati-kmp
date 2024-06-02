package core.database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import java.nio.file.Paths

object PlatformDatabase {

    private fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
        val dbFile = Paths.get("").toAbsolutePath().resolve("room_database").resolve("room_database.db")
        return Room.databaseBuilder<AppDatabase>(
            name = dbFile.toString(),
            factory =  { AppDatabase::class.instantiateImpl() }
        ).setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
    }


    fun getDatabase(): AppDatabase {
        return getDatabaseBuilder().build()
    }
}