package dev.lkeeeey.edu.main.presentation.calendar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import dev.lkeeeey.edu.auth.data.keys.Keys
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class CalendarViewModel (

) : ViewModel() {

    private val _state = MutableStateFlow(CalendarState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val settings = Settings()

    fun onEvent(event : CalendarEvent) {
        when (event) {
            CalendarEvent.OnBtnClick -> {
                println("maaaaain - ${settings.get<String>(key = Keys.ACCESS_TOKEN)}  ${settings.get<String>(key = Keys.REFRESH_TOKEN)}")
            }
        }
    }

}
