package feature.imageparsing.usecase

import feature.imageparsing.model.Language
import feature.imageparsing.model.ParsingResult
import feature.imageparsing.repository.ImageParsingRepository

class ParseImageByUrlUseCase(
    private val repository: ImageParsingRepository
) {

    suspend operator fun invoke(url: String, language: Language): ParsingResult {
        return repository.parseImageByUrl(url, language)
    }
}