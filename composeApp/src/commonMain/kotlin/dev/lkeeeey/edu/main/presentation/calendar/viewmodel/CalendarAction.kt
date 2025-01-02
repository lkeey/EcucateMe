package dev.lkeeeey.edu.main.presentation.calendar.viewmodel

sealed interface CalendarAction {
    data object OnOpenProfile: CalendarAction
    data object OnCreateTask: CalendarAction
}