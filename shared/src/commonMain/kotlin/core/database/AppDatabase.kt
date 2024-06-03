package core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import core.database.dao.authfeature.UserDao
import core.database.dao.chartsfeature.ChartDao
import core.database.dao.imageparsingfeature.ParsingResultDao
import core.database.dao.qrcodesfeature.QRCodeRequestDao
import core.database.entity.authfeature.UserEntity
import core.database.entity.chartsfeature.ChartEntity
import core.database.entity.imageparsingfeature.ParsingResultEntity
import core.database.entity.qrcodesfeature.QRCodeRequestEntity

@Database(entities = [
    UserEntity::class,
    ChartEntity::class,
    ParsingResultEntity::class,
    QRCodeRequestEntity::class
 ], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun chartDao(): ChartDao

    abstract fun parsingResultDao(): ParsingResultDao

    abstract fun qrCodeRequestDao(): QRCodeRequestDao
}

