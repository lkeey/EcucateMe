package dev.lkeeeey.edu.auth.presentation.login.viewmodel

sealed interface LoginAction {
    data object OnLogin: LoginAction
    data object OnSignUp: LoginAction
    data object ClearEvents: LoginAction
    data class OnUsernameChanged(val username: String): LoginAction
    data class OnPasswordChanged(val password: String): LoginAction
}
