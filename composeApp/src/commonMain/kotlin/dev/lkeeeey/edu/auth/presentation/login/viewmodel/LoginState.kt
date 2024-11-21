package dev.lkeeeey.edu.auth.presentation.login.viewmodel

data class LoginState (
    val username: String = "",
    val password: String = "",

    val isLoading: Boolean = false,
    val isPasswordError: Boolean = false,

    val errorMessage: String = ""
)