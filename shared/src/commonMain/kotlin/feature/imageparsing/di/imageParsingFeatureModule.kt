package feature.imageparsing.di

import core.database.dao.imageparsingfeature.ParsingResultDao
import feature.imageparsing.data.mapper.ImageParsingMapper
import feature.imageparsing.data.network.OCRApi
import feature.imageparsing.data.repository.ImageParsingRepositoryImpl
import feature.imageparsing.domain.repository.ImageParsingRepository
import feature.imageparsing.domain.usecase.ClearHistoryUseCase
import feature.imageparsing.domain.usecase.GetParsingHistoryUseCase
import feature.imageparsing.domain.usecase.GetParsingResultByIdUseCase
import feature.imageparsing.domain.usecase.ParseImageByUrlUseCase
import feature.imageparsing.presentation.history.ParsingHistoryViewModel
import feature.imageparsing.presentation.parsing.ParsingViewModel
import io.ktor.client.HttpClient
import org.koin.dsl.module

val imageParsingFeatureModule = module {
    // Data
    factory<OCRApi> { OCRApi(get<HttpClient>()) }
    factory<ImageParsingMapper> { ImageParsingMapper() }
    factory<ImageParsingRepository> {
        ImageParsingRepositoryImpl(
            get<OCRApi>(),
            get<ParsingResultDao>(),
            get<ImageParsingMapper>(),
        )
    }

    // Domain
    factory<ClearHistoryUseCase> { ClearHistoryUseCase(get<ImageParsingRepository>()) }
    factory<GetParsingHistoryUseCase> { GetParsingHistoryUseCase(get<ImageParsingRepository>()) }
    factory<GetParsingResultByIdUseCase> { GetParsingResultByIdUseCase(get<ImageParsingRepository>()) }
    factory<ParseImageByUrlUseCase> { ParseImageByUrlUseCase(get<ImageParsingRepository>()) }

    // Presentation
    factory<ParsingViewModel> { (id: Long) ->
        ParsingViewModel(
            id,
            get<ParseImageByUrlUseCase>(),
            get<GetParsingResultByIdUseCase>()
        )
    }
    factory<ParsingHistoryViewModel> {
        ParsingHistoryViewModel(
            get<GetParsingHistoryUseCase>(),
            get<ClearHistoryUseCase>()
        )
    }
}