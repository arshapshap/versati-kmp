package feature.qrcodes.domain.repository

import feature.qrcodes.domain.model.QRCodeInfo

interface QRCodesRepository {

    suspend fun createQRCodeImageUrl(qrCode: QRCodeInfo): String

    suspend fun getQRCodesHistory(): List<QRCodeInfo>

    suspend fun getQRCodeInfoById(id: Long): QRCodeInfo?

    suspend fun clearHistory()
}