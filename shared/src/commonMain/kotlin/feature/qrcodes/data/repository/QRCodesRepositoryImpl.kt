package feature.qrcodes.data.repository

import core.database.dao.qrcodesfeature.QRCodeRequestDao
import feature.qrcodes.data.mapper.QRCodesMapper
import feature.qrcodes.domain.model.QRCodeInfo
import feature.qrcodes.domain.repository.QRCodesRepository

private const val MAX_HISTORY_SIZE = 20

internal class QRCodesRepositoryImpl(
    private val dao: QRCodeRequestDao,
    private val mapper: QRCodesMapper,
) : QRCodesRepository {

    override suspend fun createQRCodeImageUrl(qrCode: QRCodeInfo): String {
        val imageUrl = mapper.createImageUrl(qrCode)
        dao.add(mapper.mapToLocal(qrCode, 0, imageUrl))
        if (dao.getCount() > MAX_HISTORY_SIZE)
            dao.deleteOldest()
        return imageUrl
    }

    override suspend fun getQRCodesHistory(): List<QRCodeInfo> {
        return dao.getAll().map { mapper.mapFromLocal(it) }
    }

    override suspend fun getQRCodeInfoById(id: Long): QRCodeInfo? {
        return dao.getById(id)?.let { mapper.mapFromLocal(it) }
    }

    override suspend fun clearHistory() {
        dao.deleteAll()
    }
}