package feature.qrcodes.utils

@OptIn(ExperimentalStdlibApi::class)
internal fun Int.toHex() = toHexString().padStart(6, '0').drop(2)