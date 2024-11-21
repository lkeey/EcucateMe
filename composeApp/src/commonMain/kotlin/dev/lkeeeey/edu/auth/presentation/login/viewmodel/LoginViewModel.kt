package dev.lkeeeey.edu.auth.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
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

            LoginAction.OnSignUp -> {  }
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

        println("enabled ${state.value.isButtonEnabled} ${state.value.username} ${state.value.password}")

    }

    private fun login() {
        // DO some logic

        _state.update {
            it.copy(
                isLoading = true
            )
        }

        println("send request with ${state.value.username} ${state.value.password}")
    }
}
