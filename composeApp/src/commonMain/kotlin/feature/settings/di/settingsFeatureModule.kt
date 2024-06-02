package feature.settings.di

import feature.auth.domain.usecase.GetCurrentUserUseCase
import feature.auth.domain.usecase.LogOutUseCase
import feature.settings.presentation.SettingsViewModel
import org.koin.dsl.module

val settingsFeatureModule = module {
    factory<SettingsViewModel> {
        SettingsViewModel(
            get<GetCurrentUserUseCase>(),
            get<LogOutUseCase>()
        )
    }
}