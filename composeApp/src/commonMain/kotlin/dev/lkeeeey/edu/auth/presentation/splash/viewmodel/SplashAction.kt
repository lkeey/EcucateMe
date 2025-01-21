package dev.lkeeeey.edu.auth.presentation.splash.viewmodel

sealed interface SplashAction {
    data object Nothing : SplashAction
    data object OpenMain : SplashAction
    data object OpenLogin : SplashAction
}