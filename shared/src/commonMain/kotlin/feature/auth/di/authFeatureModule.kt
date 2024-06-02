package feature.auth.di

import com.russhwolf.settings.Settings
import core.database.dao.authfeature.UserDao
import feature.auth.data.helper.AuthSettingsHelper
import feature.auth.data.mapper.AuthMapper
import feature.auth.data.repository.AuthRepositoryImpl
import feature.auth.domain.repository.AuthRepository
import feature.auth.domain.usecase.GetCurrentUserUseCase
import feature.auth.domain.usecase.LogOutUseCase
import feature.auth.domain.usecase.RegisterUseCase
import feature.auth.domain.usecase.SignInUseCase
import feature.auth.presentation.register.RegisterViewModel
import feature.auth.presentation.signin.SignInViewModel
import org.koin.dsl.module

val authFeatureModule = module {
    // Data
    factory<Settings> { Settings() }
    factory<AuthSettingsHelper> { AuthSettingsHelper(get<Settings>()) }
    factory<AuthMapper> { AuthMapper() }
    factory<AuthRepository> { AuthRepositoryImpl(get<UserDao>(), get<AuthMapper>(), get<AuthSettingsHelper>()) }

    // Domain
    factory<GetCurrentUserUseCase> { GetCurrentUserUseCase(get<AuthRepository>()) }
    factory<LogOutUseCase> { LogOutUseCase(get<AuthRepository>()) }
    factory<RegisterUseCase> { RegisterUseCase(get<AuthRepository>()) }
    factory<SignInUseCase> { SignInUseCase(get<AuthRepository>()) }

    // Presentation
    factory<RegisterViewModel> { RegisterViewModel(get<RegisterUseCase>()) }
    factory<SignInViewModel> { SignInViewModel(get<SignInUseCase>()) }
}