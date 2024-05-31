package feature.charts.data.utils

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder

inline fun <reified T> T.toJson(): String = Json.encodeToString(this)

inline fun <reified T> String.fromJson(): T = Json.decodeFromString<T>(this)

fun String.encodeUrl(): String = URLEncoder.encode(this, "utf-8")