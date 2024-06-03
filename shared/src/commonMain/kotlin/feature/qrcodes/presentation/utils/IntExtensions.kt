package feature.qrcodes.presentation.utils

@OptIn(ExperimentalStdlibApi::class)
fun Int.toHex() = toHexString().padStart(6, '0').drop(2)