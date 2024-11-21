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
            }
            is RegisterAction.OnLogin -> { }
            is RegisterAction.OnPasswordChanged -> {
                _state.update {
                    it.copy(
                        password = event.password
                    )
                }
            }
            is RegisterAction.OnSignUp -> signup()
            is RegisterAction.OnUsernameChanged -> {
                _state.update {
                    it.copy(
                        username = event.username
                    )
                }
            }
        }
    }

    private fun signup() {
        // sign up
    }
}
