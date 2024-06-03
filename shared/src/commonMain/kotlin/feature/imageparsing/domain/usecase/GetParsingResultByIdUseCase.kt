package feature.imageparsing.domain.usecase

import feature.imageparsing.domain.model.ParsingResult
import feature.imageparsing.domain.repository.ImageParsingRepository

class GetParsingResultByIdUseCase(
    private val repository: ImageParsingRepository
) {

    suspend operator fun invoke(id: Long): ParsingResult? {
        return repository.getParsingResultById(id)
    }
}