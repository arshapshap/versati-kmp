package feature.charts.di

import core.database.dao.chartsfeature.ChartDao
import feature.charts.data.mapper.ChartsMapper
import feature.charts.data.repository.ChartsRepositoryImpl
import feature.charts.domain.repository.ChartsRepository
import feature.charts.domain.usecase.ClearHistoryUseCase
import feature.charts.domain.usecase.CreateChartUseCase
import feature.charts.domain.usecase.GetChartInfoByIdUseCase
import feature.charts.domain.usecase.GetChartsHistoryUseCase
import feature.charts.presentation.chartgeneration.ChartGenerationViewModel
import feature.charts.presentation.chartgeneration.mapper.ChartGenerationStateMapper
import feature.charts.presentation.chartshistory.ChartsHistoryViewModel
import org.koin.dsl.module

val chartsFeatureModule = module {
    // Data
    factory<ChartsMapper> { ChartsMapper() }
    factory<ChartsRepository> {
        ChartsRepositoryImpl(
            dao = get<ChartDao>(),
            mapper = get<ChartsMapper>()
        )
    }

    // Domain
    factory<ClearHistoryUseCase> { ClearHistoryUseCase(repository = get<ChartsRepository>()) }
    factory<CreateChartUseCase> { CreateChartUseCase(repository = get<ChartsRepository>()) }
    factory<GetChartInfoByIdUseCase> { GetChartInfoByIdUseCase(repository = get<ChartsRepository>()) }
    factory<GetChartsHistoryUseCase> { GetChartsHistoryUseCase(repository = get<ChartsRepository>()) }

    // Presentation
    factory<ChartGenerationStateMapper> { ChartGenerationStateMapper() }
    factory<ChartGenerationViewModel> { (id: Long) ->
        ChartGenerationViewModel(
            chartInfoId = id,
            createChartUseCase = get<CreateChartUseCase>(),
            getChartInfoByIdUseCase = get<GetChartInfoByIdUseCase>(),
            mapper = get<ChartGenerationStateMapper>()
        )
    }
    factory<ChartsHistoryViewModel> {
        ChartsHistoryViewModel(get<GetChartsHistoryUseCase>(), get<ClearHistoryUseCase>())
    }
}