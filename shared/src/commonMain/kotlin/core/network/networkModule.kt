package core.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.HttpRequest
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single<HttpClient> { HttpClient {
        defaultRequest {
            host = "api.ocr.space/parse"
            url {
                protocol = URLProtocol.HTTPS
            }
        }
        HttpResponseValidator {
            handleResponseExceptionWithRequest { cause: Throwable, _: HttpRequest ->
                handleException(cause)
            }
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    } }
}