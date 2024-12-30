package dev.lkeeeey.edu.main.presentation.profile.timetable.viewmodel

import dev.lkeeeey.edu.main.domain.models.SubjectPresModel

sealed interface TimeTableEvent {
    data class OnSubjectUpdate(
        val deletedSubject : Int,
        val subjectNew: SubjectPresModel,
        val subjectNum: Int
    ) : TimeTableEvent
    data class OnChangeDay(val index : Int): TimeTableEvent
    data object OnAddLesson : TimeTableEvent
    data object OnSaveDay : TimeTableEvent
}