package dev.lkeeeey.edu.main.presentation.calendar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class CalendarViewModel : ViewModel() {

    private val _state = MutableStateFlow(CalendarState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val settings = Settings()

    fun onEvent(event : CalendarEvent) {
        when (event) {
            is CalendarEvent.OnDayClick -> {
                _state.update {
                    it.copy(
                        selectedDate = event.date
                    )
                }
            }
        }
    }
}
