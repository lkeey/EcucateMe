package dev.lkeeeey.edu.auth.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russhwolf.settings.Settings
import dev.lkeeeey.edu.auth.data.keys.Keys
import dev.lkeeeey.edu.auth.domain.AuthRepository
import dev.lkeeeey.edu.auth.domain.models.LoginRequest
import dev.lkeeeey.edu.core.domain.onError
import dev.lkeeeey.edu.core.domain.onSuccess
import dev.lkeeeey.edu.core.presentation.toStr
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel (
    private val authRepository: AuthRepository
) : ViewModel() {
    private val settings = Settings()

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
                    query = LoginRequest(
                        username = state.value.username,
                        password = state.value.password,
                    )
                )
                .onSuccess { response->
                    authRepository
                        .updateAccessToken(
                            access = response.access
                        )
                        .onSuccess {
                            authRepository
                                .updateAuthenticated(true)
                                .onSuccess {

                                    settings.putString(
                                        key = Keys.LOGIN,
                                        value = state.value.username
                                    )

                                    settings.putString(
                                        key = Keys.PASSWORD,
                                        value = state.value.password
                                    )

                                    settings.putString(
                                        key = Keys.MY_USERNAME,
                                        value = state.value.username
                                    )

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
                        }.onError { error->
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
