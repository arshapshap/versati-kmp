package feature.charts.data.utils

import java.net.URLEncoder

actual fun String.encodeUrl(): String = URLEncoder.encode(this, "utf-8")