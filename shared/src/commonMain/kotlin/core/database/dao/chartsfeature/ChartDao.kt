package core.database.dao.chartsfeature

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import core.database.entity.chartsfeature.ChartEntity

@Dao
interface ChartDao {

    @Insert
    suspend fun add(chart: ChartEntity): Long

    @Query("DELETE FROM Chart WHERE id IN (SELECT id FROM Chart ORDER BY id ASC LIMIT 1)")
    suspend fun deleteOldest()

    @Query("DELETE FROM Chart")
    suspend fun deleteAll()

    @Query("SELECT * FROM Chart")
    suspend fun getAll(): List<ChartEntity>

    @Query("SELECT COUNT(id) FROM Chart")
    suspend fun getCount(): Int

    @Query("SELECT * FROM Chart WHERE id = :id")
    suspend fun getById(id: Long): ChartEntity?
}