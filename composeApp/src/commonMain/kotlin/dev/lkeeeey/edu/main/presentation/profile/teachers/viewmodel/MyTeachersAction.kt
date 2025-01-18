package dev.lkeeeey.edu.main.presentation.profile.teachers.viewmodel

sealed interface MyTeachersAction {
    data object OnOpenTeacherDescription : MyTeachersAction
    data object OnOpenBack : MyTeachersAction
}