package dev.lkeeeey.edu.auth.presentation.login.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class LoginViewModel (

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
                    isButtonEnabled = true
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

        // TODO some logic

        _state.update {
            it.copy(
                event = LoginEvent.OpenMain
            )
        }

    }
}
