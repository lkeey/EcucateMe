package dev.lkeeeey.edu.library.presentation.teachers.viewmodel

sealed interface AllTeachersEvent {
    data class OnSubjectUpdate(val subject: String) : AllTeachersEvent
    data object OnSearchTeachers : AllTeachersEvent
}