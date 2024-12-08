package dev.lkeeeey.edu.main.presentation.calendar.viewmodel

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

data class CalendarState (
    val subjects: List<String> = emptyList(),

//    val loadedDates: List<LocalDate> = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.getWeek(),
    val loadedDates: List<LocalDate> =
        List(7) { Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date },


    val currentDate: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
    val selectedDate: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
)

