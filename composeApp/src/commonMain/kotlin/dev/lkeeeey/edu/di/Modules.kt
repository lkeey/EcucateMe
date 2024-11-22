package dev.lkeeeey.edu.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import dev.lkeeeey.edu.auth.presentation.splash.viewmodel.SplashViewModel
import dev.lkeeeey.edu.auth.presentation.login.viewmodel.LoginViewModel
import dev.lkeeeey.edu.auth.presentation.register.viewmodel.RegisterViewModel
import dev.lkeeeey.edu.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import dev.lkeeeey.edu.auth.data.network.KtorRemoteBookDataSource
import dev.lkeeeey.edu.auth.data.network.RemoteAuthDataSource
import dev.lkeeeey.edu.auth.data.repository.AuthRepositoryImpl
import dev.lkeeeey.edu.auth.domain.AuthRepository
import org.koin.dsl.bind

expect val platformModule: Module

val sharedModule = module {

    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteAuthDataSource>()
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()

    viewModelOf(::SplashViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)

}
