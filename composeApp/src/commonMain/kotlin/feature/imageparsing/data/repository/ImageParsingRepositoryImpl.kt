package feature.imageparsing.data.repository

import core.database.dao.imageparsingfeature.ParsingResultDao
import feature.imageparsing.data.mapper.ImageParsingMapper
import feature.imageparsing.data.network.OCRApi
import feature.imageparsing.domain.model.Language
import feature.imageparsing.domain.model.ParsingResult
import feature.imageparsing.domain.repository.ImageParsingRepository

// TODO: перенести в норм место
private const val OCR_API_KEY = "K87297716488957"
private const val MAX_HISTORY_SIZE = 20

internal class ImageParsingRepositoryImpl(
    private val api: OCRApi,
    private val dao: ParsingResultDao,
    private val mapper: ImageParsingMapper
) : ImageParsingRepository {

    override suspend fun parseImageByUrl(url: String, language: Language): ParsingResult {
        val requestBody = mapper.mapToMultiPartForm(url, language)
        val response = api.parseImageByUrl(OCR_API_KEY, requestBody)
        val parsingResult = mapper.mapFromRemote(remote = response, id = 0, sourceUrl = url)

        if (parsingResult.ocrExitCode == 1) {
            val local = mapper.mapToLocal(parsingResult)
            val id = dao.add(local)
            if (dao.getCount() > MAX_HISTORY_SIZE)
                dao.deleteOldest()
            return parsingResult.copy(id = id)
        }

        return parsingResult
    }

    override suspend fun getParsingHistory(): List<ParsingResult> {
        return dao.getAll().map(mapper::mapFromLocal)
    }

    override suspend fun clearHistory() {
        dao.deleteAll()
    }

    override suspend fun getParsingResultById(id: Long): ParsingResult? {
        return dao.getById(id)?.let { mapper.mapFromLocal(it) }
    }
}