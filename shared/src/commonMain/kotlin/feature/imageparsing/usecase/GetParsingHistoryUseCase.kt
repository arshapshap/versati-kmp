package feature.imageparsing.usecase

import feature.imageparsing.model.ParsingResult
import feature.imageparsing.repository.ImageParsingRepository

class GetParsingHistoryUseCase(
    private val repository: ImageParsingRepository
) {

    suspend operator fun invoke(): List<ParsingResult> {
        return repository.getParsingHistory()
    }
}