package dev.lkeeeey.edu.auth.presentation.splash.viewmodel

sealed interface SplashEvent {
    data object ClearEvents: SplashEvent
}