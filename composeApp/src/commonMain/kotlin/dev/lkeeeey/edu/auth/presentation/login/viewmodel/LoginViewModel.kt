package dev.lkeeeey.edu.auth.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

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
            LoginAction.OnLogin -> TODO()
            is LoginAction.OnPasswordChanged -> TODO()
            is LoginAction.OnUsernameChanged -> TODO()
        }
    }
}
