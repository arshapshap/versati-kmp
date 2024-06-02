package core.database.dao.imageparsingfeature

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import core.database.entity.imageparsingfeature.ParsingResultEntity

@Dao
interface ParsingResultDao {

    @Insert
    suspend fun add(parsingResult: ParsingResultEntity): Long

    @Query("DELETE FROM ParsingResult WHERE id IN (SELECT id FROM ParsingResult ORDER BY id ASC LIMIT 1)")
    suspend fun deleteOldest()

    @Query("DELETE FROM ParsingResult")
    suspend fun deleteAll()

    @Query("SELECT * FROM ParsingResult")
    suspend fun getAll(): List<ParsingResultEntity>

    @Query("SELECT * FROM ParsingResult WHERE id=:id")
    suspend fun getById(id: Long): ParsingResultEntity?

    @Query("SELECT COUNT(id) FROM PARSINGRESULT")
    suspend fun getCount(): Int
}