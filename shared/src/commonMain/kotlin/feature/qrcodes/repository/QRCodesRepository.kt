package feature.qrcodes.repository

import feature.qrcodes.model.QRCodeInfo

interface QRCodesRepository {

    suspend fun createQRCodeImageUrl(options: QRCodeInfo): String

    suspend fun getQRCodesHistory(): List<QRCodeInfo>

    suspend fun getQRCodeInfoById(id: Long): QRCodeInfo?

    suspend fun clearHistory()
}