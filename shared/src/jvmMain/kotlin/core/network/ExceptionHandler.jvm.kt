package core.network

import core.network.exception.NetworkException
import core.network.exception.TimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import java.net.UnknownHostException

actual fun handleException(cause: Throwable): Nothing {
    when (cause) {
        is UnknownHostException -> throw NetworkException()
        is SocketTimeoutException -> throw TimeoutException()
        else -> throw cause
    }
}