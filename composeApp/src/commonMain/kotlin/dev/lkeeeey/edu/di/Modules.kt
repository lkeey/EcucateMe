package dev.lkeeeey.edu.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import dev.lkeeeey.edu.auth.presentation.splash.viewmodel.SplashViewModel
import dev.lkeeeey.edu.auth.presentation.login.viewmodel.LoginViewModel
import dev.lkeeeey.edu.auth.presentation.register.viewmodel.RegisterViewModel
import org.koin.core.module.Module

expect val platformModule: Module

val sharedModule = module {

    viewModelOf(::SplashViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)

}
