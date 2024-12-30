package dev.lkeeeey.edu.profile.presentation.viewmodel

sealed interface ProfileAction {
    data object OnOpenTimeTable: ProfileAction
    data object OnOpenSubjects: ProfileAction
    data object OnOpenLogin: ProfileAction
}