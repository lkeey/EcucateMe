package dev.lkeeeey.edu.auth.presentation.register.viewmodel

data class RegisterState(
    val username: String = "",
    val password: String = "",
    val confirmedPassword: String = "",

    val isLoading: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val isError: Boolean = false,

    val errorMessage: String = "",

    val event: RegisterEvent = RegisterEvent.Nothing,
)
