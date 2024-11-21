package dev.lkeeeey.edu.auth.presentation.register.viewmodel

import dev.lkeeeey.edu.auth.presentation.login.viewmodel.LoginEvent

sealed interface RegisterEvent {
    data object OpenLogin : RegisterEvent
    data object OpenMain : RegisterEvent
    data object Nothing : RegisterEvent
}
