package dev.lkeeeey.edu.auth.presentation.login.viewmodel

sealed interface LoginAction {
    data object OnLogin: LoginAction
    data class OnUsernameChanged(val username: String): LoginAction
    data class OnPasswordChanged(val password: String): LoginAction
}
