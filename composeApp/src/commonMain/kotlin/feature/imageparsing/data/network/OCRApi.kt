package feature.imageparsing.data.network

import feature.imageparsing.data.network.response.ImageParsingResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody

internal class OCRApi(
    private val httpClient: HttpClient
) {

    suspend fun parseImageByUrl(
        apiKey: String,
        body: MultiPartFormDataContent
    ): ImageParsingResult {
        return httpClient.post("/image") {
            setBody(body)
            header("Apikey", apiKey)
        }.body<ImageParsingResult>()
    }
}