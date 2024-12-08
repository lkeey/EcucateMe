package dev.lkeeeey.edu.main.presentation.calendar.viewmodel

import kotlinx.datetime.LocalDate

sealed interface CalendarEvent {
    data class OnDayClick(val date: LocalDate) : CalendarEvent
}