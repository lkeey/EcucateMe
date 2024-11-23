package dev.lkeeeey.edu.auth.presentation.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkeeeey.edu.auth.data.database.UserEntity
import dev.lkeeeey.edu.auth.domain.AuthRepository
import dev.lkeeeey.edu.auth.domain.models.LoginRequest
import dev.lkeeeey.edu.auth.domain.models.RegisterRequest
import dev.lkeeeey.edu.core.domain.onError
import dev.lkeeeey.edu.core.domain.onSuccess
import dev.lkeeeey.edu.core.presentation.toStr
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel (
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )


    fun onAction(event: RegisterAction) {
        when (event) {
            is RegisterAction.OnConfirmedPasswordChanged -> {
                _state.update {
                    it.copy(
                        confirmedPassword = event.password
                    )
                }

                checkEnabled()
            }
            is RegisterAction.OnLogin -> {
                _state.update {
                    it.copy(
                        event = RegisterEvent.OpenLogin
                    )
                }
            }
            is RegisterAction.OnPasswordChanged -> {
                _state.update {
                    it.copy(
                        password = event.password
                    )
                }

                checkEnabled()
            }
            is RegisterAction.OnSignUp -> signup()
            is RegisterAction.OnUsernameChanged -> {
                _state.update {
                    it.copy(
                        username = event.username
                    )
                }

                checkEnabled()
            }

            RegisterAction.ClearEvents -> {
                _state.update {
                    it.copy(
                        event = RegisterEvent.Nothing
                    )
                }
            }
        }
    }

    private fun checkEnabled() {
        if (state.value.password.isNotEmpty() && state.value.username.isNotEmpty() && state.value.confirmedPassword.isNotEmpty()) {
            _state.update {
                it.copy(
                    isButtonEnabled = true,
                    isError = false
                )
            }
        } else {
            _state.update {
                it.copy(
                    isButtonEnabled = false
                )
            }
        }
    }

    private fun signup() {
        _state.update {
            it.copy(
                isLoading = true,
                isButtonEnabled = false
            )
        }

        // sign up
        viewModelScope.launch {
            authRepository.registerUser(
                query = RegisterRequest(
                    username = state.value.username,
                    name = state.value.username,
                    password = state.value.password,
                    accountType = "student",
                )
            )
            .onSuccess {

                // login user
                authRepository
                    .loginUser(
                        saveCookies = { cookie->
                            viewModelScope.launch {
                                authRepository.updateRefreshToken(
                                    refresh = cookie
                                )
                            }
                        },
                        query = LoginRequest(
                            username = state.value.username,
                            password = state.value.password,
                        )
                    )
                    .onSuccess { response->
                        println("access token is us in register - $response.access")

                        authRepository.updateAccessToken(
                            access = response.access
                        )

                        val user : List<UserEntity>? = authRepository
                            .getUserEntity()
                            .firstOrNull()

                        println("all user - ${user?.get(0)}")

                        _state.update {
                            it.copy(
                                isLoading = false,
                                event = RegisterEvent.OpenMain
                            )
                        }
                    }
                    .onError { error->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                isError = true,
                                errorMessage = error.toStr()
                            )
                        }
                    }
            }
            .onError { error->
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                        errorMessage = error.toStr()
                    )
                }
            }
        }
    }
}
