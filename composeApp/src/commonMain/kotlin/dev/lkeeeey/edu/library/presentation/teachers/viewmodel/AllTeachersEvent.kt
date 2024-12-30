package dev.lkeeeey.edu.library.presentation.teachers.viewmodel

sealed interface AllTeachersEvent {
    data class OnSubjectUpdate(val subject: String) : AllTeachersEvent
    data class OnOpenTeacherDescription(val username: String) : AllTeachersEvent
    data object OnSearchTeachers : AllTeachersEvent
    data object OnSelectTeacher : AllTeachersEvent
    data object OnLoadTeacherDescription : AllTeachersEvent
}