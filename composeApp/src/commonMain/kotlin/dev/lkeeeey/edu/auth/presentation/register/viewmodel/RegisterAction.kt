package dev.lkeeeey.edu.auth.presentation.register.viewmodel

sealed interface RegisterAction {
    data object OnLogin: RegisterAction
    data object OnSignUp: RegisterAction
    data object ClearEvents: RegisterAction
    data class OnUsernameChanged(val username: String): RegisterAction
    data class OnPasswordChanged(val password: String): RegisterAction
    data class OnConfirmedPasswordChanged(val password: String): RegisterAction
}