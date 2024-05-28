package com.arshapshap.versati.kmp.feature.auth.di

import com.arshapshap.versati.kmp.feature.auth.data.AuthRepositoryImpl
import com.arshapshap.versati.kmp.feature.auth.presentation.register.RegisterViewModel
import com.arshapshap.versati.kmp.feature.auth.presentation.signin.SignInViewModel
import feature.auth.domain.repository.AuthRepository
import feature.auth.domain.usecase.GetCurrentUserUseCase
import feature.auth.domain.usecase.LogOutUseCase
import feature.auth.domain.usecase.RegisterUseCase
import feature.auth.domain.usecase.SignInUseCase
import org.koin.dsl.module

val authFeatureModule = module {
    // Data
//    factory<AuthMapper> { AuthMapper() }
    factory<AuthRepository> { AuthRepositoryImpl() }

    // Domain
    factory<GetCurrentUserUseCase> { GetCurrentUserUseCase(get<AuthRepository>()) }
    factory<LogOutUseCase> { LogOutUseCase(get<AuthRepository>()) }
    factory<RegisterUseCase> { RegisterUseCase(get<AuthRepository>()) }
    factory<SignInUseCase> { SignInUseCase(get<AuthRepository>()) }

    // Presentation
    factory<RegisterViewModel> { RegisterViewModel(get<RegisterUseCase>()) }
    factory<SignInViewModel> { SignInViewModel(get<SignInUseCase>()) }
}