package dev.lkeeeey.edu.main.presentation.calendar.viewmodel

sealed interface CalendarEvent {
    data object OnBtnClick : CalendarEvent
}