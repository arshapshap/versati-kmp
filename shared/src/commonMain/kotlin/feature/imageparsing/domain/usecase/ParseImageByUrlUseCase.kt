package feature.imageparsing.domain.usecase

import feature.imageparsing.domain.model.Language
import feature.imageparsing.domain.model.ParsingResult
import feature.imageparsing.domain.repository.ImageParsingRepository

class ParseImageByUrlUseCase(
    private val repository: ImageParsingRepository
) {

    suspend operator fun invoke(url: String, language: Language): ParsingResult {
        return repository.parseImageByUrl(url, language)
    }
}