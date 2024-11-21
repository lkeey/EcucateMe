package dev.lkeeeey.edu.auth.presentation.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class RegisterViewModel (

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

    private fun signup() {
        // sign up

        _state.update {
            it.copy(
                isLoading = true,
                isButtonEnabled = false
            )
        }

        _state.update {
            it.copy(
                event = RegisterEvent.OpenMain
            )
        }
    }
}
