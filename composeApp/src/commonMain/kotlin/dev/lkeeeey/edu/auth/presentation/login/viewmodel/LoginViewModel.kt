package dev.lkeeeey.edu.auth.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkeeeey.edu.auth.data.database.UserEntity
import dev.lkeeeey.edu.auth.domain.AuthRepository
import dev.lkeeeey.edu.auth.domain.models.LoginRequest
import dev.lkeeeey.edu.core.domain.onError
import dev.lkeeeey.edu.core.domain.onSuccess
import dev.lkeeeey.edu.core.presentation.toStr
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel (
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: LoginAction) {
        when (action) {
            LoginAction.OnLogin -> login()
            is LoginAction.OnPasswordChanged -> {
                _state.update {
                    it.copy(
                        password = action.password
                    )
                }

                checkEnabled()
            }
            is LoginAction.OnUsernameChanged -> {
                _state.update {
                    it.copy(
                        username = action.username
                    )
                }

                checkEnabled()
            }

            is LoginAction.OnSignUp -> {
                _state.update {
                    it.copy(
                        event = LoginEvent.OpenSignUp
                    )
                }
            }

            is LoginAction.ClearEvents -> {
                _state.update {
                    it.copy(
                        event = LoginEvent.Nothing
                    )
                }
            }
        }
    }

    private fun checkEnabled() {
        if (state.value.password.isNotEmpty() && state.value.username.isNotEmpty()) {
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

    private fun login() {

        _state.update {
            it.copy(
                isLoading = true,
                isButtonEnabled = false
            )
        }

        // login
        viewModelScope.launch {
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
                    println("access token is us - ${response.access}")

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
                            event = LoginEvent.OpenMain
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
    }
}
