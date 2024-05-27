package feature.imageparsing.usecase

import feature.imageparsing.model.ParsingResult
import feature.imageparsing.repository.ImageParsingRepository

class GetParsingResultByIdUseCase(
    private val repository: ImageParsingRepository
) {

    suspend operator fun invoke(id: Long): ParsingResult? {
        return repository.getParsingResultById(id)
    }
}