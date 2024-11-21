package dev.lkeeeey.edu.auth.presentation.register.viewmodel

data class RegisterState(
    val username: String = "",
    val password: String = "",
    val confirmedPassword: String = "",

    val passwordError: String? = null,

    val isLoading: Boolean = false,
    val isButtonEnabled: Boolean = false,
)
