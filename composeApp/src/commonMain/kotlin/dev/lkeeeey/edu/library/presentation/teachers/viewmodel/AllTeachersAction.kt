package dev.lkeeeey.edu.library.presentation.teachers.viewmodel

sealed interface AllTeachersAction {
    data object OnOpenTeacherDescription : AllTeachersAction
    data object OnOpenBack : AllTeachersAction
}
