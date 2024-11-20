package dev.lkeeeey.edu.auth.presentation.login.viewmodel

import dev.lkeeeey.edu.core.presentation.UiText

data class LoginState (
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null
)