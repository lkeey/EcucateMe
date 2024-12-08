package dev.lkeeeey.edu.auth.presentation.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russhwolf.settings.Settings
import dev.lkeeeey.edu.auth.data.database.UserEntity
import dev.lkeeeey.edu.auth.domain.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel (
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SplashState())
    val state = _state.asStateFlow()

    private val settings = Settings()

    init {
        // fetch token
        checkIfUserAuthenticated()
    }

    fun onEvent(
        event: SplashEvent
    ) {
        when (event) {
            SplashEvent.ClearEvents -> {
                _state.update {
                    it.copy(
                        action = SplashAction.Nothing
                    )
                }
            }
        }
    }

    private fun checkIfUserAuthenticated() {
        viewModelScope.launch(Dispatchers.IO) {

            authRepository.deleteAllUsers()

            // settings
            settings.putString("access", "hello from splash")
            // end settings

            val users : List<UserEntity>? = authRepository
                .getUserEntity()
                .firstOrNull()

            println("users1 - $users")

            if (users.isNullOrEmpty() || users[0].accessToken.isEmpty() || users[0].refreshToken.isEmpty()) {
                // if user created
                authRepository.addUser(
                    UserEntity(
                        id = "1",
                        username = "none username",
                        refreshToken = "",
                        accessToken = ""
                    )
                )
                val newUsers : List<UserEntity>? = authRepository
                    .getUserEntity()
                    .firstOrNull()

                println("create - " + newUsers?.get(0)?.refreshToken)


                // if update
                newUsers?.get(0)?.copy()?.let {
                    authRepository
                        .updateUser(it.copy(
                            refreshToken = "refresh 2"
                        ))
                }
                val newUsers2 : List<UserEntity>? = authRepository
                    .getUserEntity()
                    .firstOrNull()

                println("update - " + newUsers2?.get(0)?.refreshToken)

                // navigate to login
                _state.update {
                    it.copy(
                        action = SplashAction.OpenLogin
                    )
                }
            } else {
                _state.update {
                    it.copy(
                        action = SplashAction.OpenMain
                    )
                }
            }
        }
    }


}
