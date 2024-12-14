package dev.lkeeeey.edu.main.presentation.profile.timetable.timetable

sealed interface TimeTableEvent {
    data class OnSubjectUpdate(val subject: String, val index: Int) : TimeTableEvent
    data class OnChangeDay(val index : Int): TimeTableEvent
    data object OnAddLesson : TimeTableEvent
    data object OnSaveDay : TimeTableEvent
}