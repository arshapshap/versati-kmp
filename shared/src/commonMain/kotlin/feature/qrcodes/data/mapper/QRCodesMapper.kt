package feature.qrcodes.data.mapper

import core.database.entity.qrcodesfeature.QRCodeRequestEntity
import feature.qrcodes.domain.model.ImageFormat
import feature.qrcodes.domain.model.QRCodeInfo
import feature.qrcodes.presentation.utils.toHex

// TODO: вынести куда-то в норм место
private const val GOQR_BASE_URL = "https://api.qrserver.com/v1/"

internal class QRCodesMapper {

    fun createImageUrl(options: QRCodeInfo): String = with(options) {
        val url = StringBuilder(GOQR_BASE_URL)
        url.append("create-qr-code/")
        url.append("?data=$data")
        url.append("&size=${size}x${size}")
        url.append("&color=${color.toHex()}")
        url.append("&bgcolor=${backgroundColor.toHex()}")
        url.append("&qzone=$quietZone")
        url.append("&format=${format.name.lowercase()}")

        return url.toString()
    }

    fun mapToLocal(options: QRCodeInfo, id: Long, imageUrl: String): QRCodeRequestEntity {
        return QRCodeRequestEntity(
            id = id,
            data = options.data,
            size = options.size,
            color = options.color,
            backgroundColor = options.backgroundColor,
            quietZone = options.quietZone,
            format = options.format.name.lowercase(),
            imageUrl = imageUrl
        )
    }

    fun mapFromLocal(local: QRCodeRequestEntity): QRCodeInfo {
        return QRCodeInfo(
            id = local.id,
            data = local.data,
            size = local.size,
            color = local.color,
            backgroundColor = local.backgroundColor,
            quietZone = local.quietZone,
            format = ImageFormat.valueOf(local.format.uppercase()),
            imageUrl = local.imageUrl
        )
    }
}