package dev.lkeeeey.edu.main.presentation.profile.main.viewmodel

sealed interface ProfileAction {
    data object OnOpenTimeTable: ProfileAction
    data object OnOpenSubjects: ProfileAction
    data object OnOpenLogin: ProfileAction
    data object OnOpenTeachers: ProfileAction
}