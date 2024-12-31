package dev.lkeeeey.edu.main.presentation.profile.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russhwolf.settings.Settings
import dev.lkeeeey.edu.auth.data.keys.Keys
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class ProfileViewModel : ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val settings = Settings()


    fun onEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.OnLogOut -> {
                settings.putString(Keys.REFRESH_TOKEN, "")
                settings.putString(Keys.ACCESS_TOKEN, "")

                settings.putString(Keys.LOGIN, "")
                settings.putString(Keys.PASSWORD, "")

                settings.putBoolean(Keys.IS_LOGIN, false)
            }
        }
    }
}
