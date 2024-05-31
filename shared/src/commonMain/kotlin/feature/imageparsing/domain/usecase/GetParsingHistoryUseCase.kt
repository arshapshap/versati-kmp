package feature.imageparsing.domain.usecase

import feature.imageparsing.domain.model.ParsingResult
import feature.imageparsing.domain.repository.ImageParsingRepository

class GetParsingHistoryUseCase(
    private val repository: ImageParsingRepository
) {

    suspend operator fun invoke(): List<ParsingResult> {
        return repository.getParsingHistory()
    }
}