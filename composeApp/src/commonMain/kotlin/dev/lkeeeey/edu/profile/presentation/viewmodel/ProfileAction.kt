package dev.lkeeeey.edu.profile.presentation.viewmodel

sealed interface ProfileAction {
    data object OnOpenTimeTable: ProfileAction
}