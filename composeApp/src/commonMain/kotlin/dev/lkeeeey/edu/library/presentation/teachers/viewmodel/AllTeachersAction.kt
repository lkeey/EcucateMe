package dev.lkeeeey.edu.library.presentation.teachers.viewmodel

sealed interface AllTeachersAction {
    data class OnTeacherClick(val username: String)
}
