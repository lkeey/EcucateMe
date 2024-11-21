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
            }
            is LoginAction.OnUsernameChanged -> {
                _state.update {
                    it.copy(
                        password = action.username
                    )
                }
            }

            LoginAction.OnSignUp -> {  }
        }
    }

    private fun login() {
        // DO some logic
    }
}
