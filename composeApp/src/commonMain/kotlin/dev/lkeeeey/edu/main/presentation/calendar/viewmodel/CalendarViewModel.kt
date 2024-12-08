package dev.lkeeeey.edu.main.presentation.calendar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

class CalendarViewModel (

) : ViewModel() {

    private val _state = MutableStateFlow(CalendarState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val settings = Settings()

    init {
        _state.update {
            it.copy(
                loadedDates = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.getWeek()
            )
        }
    }


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

    fun LocalDate.getWeek(): List<LocalDate> {
        val startDate = this.getWeekStartDate()
        val week = List(7) { index -> startDate.plus(index.toLong(), DateTimeUnit.DAY) }

        return week
    }

    fun LocalDate.getWeekStartDate(weekStartDay: DayOfWeek = DayOfWeek.MONDAY): LocalDate {
        var date = this
        while (date.dayOfWeek != weekStartDay) {
            date = date.minus(1, DateTimeUnit.DAY)
        }
        return date
    }


}
