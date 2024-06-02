package feature.qrcodes.di

import core.database.dao.qrcodesfeature.QRCodeRequestDao
import feature.qrcodes.data.mapper.QRCodesMapper
import feature.qrcodes.data.repository.QRCodesRepositoryImpl
import feature.qrcodes.domain.repository.QRCodesRepository
import feature.qrcodes.domain.usecase.ClearHistoryUseCase
import feature.qrcodes.domain.usecase.CreateQRCodeUseCase
import feature.qrcodes.domain.usecase.GetQRCodeInfoByIdUseCase
import feature.qrcodes.domain.usecase.GetQRCodesHistoryUseCase
import feature.qrcodes.presentation.qrcodegeneration.QRCodeGenerationViewModel
import feature.qrcodes.presentation.qrcodeshistory.QRCodesHistoryViewModel
import org.koin.dsl.module

val qrCodesFeatureModule = module {
    // Data
    factory<QRCodesMapper> { QRCodesMapper() }
    factory<QRCodesRepository> {
        QRCodesRepositoryImpl(
            get<QRCodeRequestDao>(),
            get<QRCodesMapper>()
        )
    }

    // Domain
    factory<ClearHistoryUseCase> { ClearHistoryUseCase(get<QRCodesRepository>()) }
    factory<CreateQRCodeUseCase> { CreateQRCodeUseCase(get<QRCodesRepository>()) }
    factory<GetQRCodeInfoByIdUseCase> { GetQRCodeInfoByIdUseCase(get<QRCodesRepository>()) }
    factory<GetQRCodesHistoryUseCase> { GetQRCodesHistoryUseCase(get<QRCodesRepository>()) }

    // Presentation
    factory<QRCodeGenerationViewModel> { (id: Long) ->
        QRCodeGenerationViewModel(id, get<CreateQRCodeUseCase>(), get<GetQRCodeInfoByIdUseCase>())
    }
    factory<QRCodesHistoryViewModel> {
        QRCodesHistoryViewModel(get<GetQRCodesHistoryUseCase>(), get<ClearHistoryUseCase>())
    }
}