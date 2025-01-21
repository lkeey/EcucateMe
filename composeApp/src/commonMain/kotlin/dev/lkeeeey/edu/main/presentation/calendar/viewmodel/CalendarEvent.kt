package dev.lkeeeey.edu.main.presentation.calendar.viewmodel

import dev.lkeeeey.edu.main.domain.models.SubjectPresModel
import kotlinx.datetime.LocalDate

sealed interface CalendarEvent {
    data class OnDayClick(val date: LocalDate) : CalendarEvent
    data class OnEnterSubject(val subject: SubjectPresModel) : CalendarEvent
    data class OnEnterExecutionTime(val time: Int) : CalendarEvent
    data class OnEnterDeadline(val deadline: LocalDate) : CalendarEvent
    data class OnEnterContent(val content: String) : CalendarEvent
    data object OnLoadSavedSubjects: CalendarEvent
    data object OnSave : CalendarEvent
}