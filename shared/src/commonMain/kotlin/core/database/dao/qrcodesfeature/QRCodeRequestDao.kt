package core.database.dao.qrcodesfeature

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import core.database.entity.qrcodesfeature.QRCodeRequestEntity

@Dao
interface QRCodeRequestDao {

    @Insert
    suspend fun add(qrCodeRequest: QRCodeRequestEntity): Long

    @Query("DELETE FROM QRCodeRequest WHERE id IN (SELECT id FROM QRCodeRequest ORDER BY id ASC LIMIT 1)")
    suspend fun deleteOldest()

    @Query("DELETE FROM QRCodeRequest")
    suspend fun deleteAll()

    @Query("SELECT * FROM QRCodeRequest")
    suspend fun getAll(): List<QRCodeRequestEntity>

    @Query("SELECT COUNT(id) FROM QRCodeRequest")
    suspend fun getCount(): Int

    @Query("SELECT * FROM QRCodeRequest WHERE id = :id")
    suspend fun getById(id: Long): QRCodeRequestEntity?
}