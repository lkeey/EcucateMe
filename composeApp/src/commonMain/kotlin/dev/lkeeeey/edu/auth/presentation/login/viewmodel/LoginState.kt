package dev.lkeeeey.edu.auth.presentation.login.viewmodel

data class LoginState (
    val username: String = "",
    val password: String = "",

    val isLoading: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val isError: Boolean = false,

    val errorMessage: String = ""
)