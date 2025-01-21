package dev.lkeeeey.edu.main.presentation.profile.teachers.viewmodel

sealed interface MyTeachersEvent {
    data class OnOpenTeacherDescription(val username: String) : MyTeachersEvent
    data object OnSearchSelectedTeachers : MyTeachersEvent
}