package dev.lkeeeey.edu.main.presentation.profile.timetable.viewmodel

sealed interface TimeTableEvent {
    data class OnSubjectUpdate(
        val deletedSubjectId : Int,
        val subjectID: Int,
        val subjectNum: Int
    ) : TimeTableEvent
    data class OnChangeDay(val index : Int): TimeTableEvent
    data object OnAddLesson : TimeTableEvent
    data object OnSaveDay : TimeTableEvent
}