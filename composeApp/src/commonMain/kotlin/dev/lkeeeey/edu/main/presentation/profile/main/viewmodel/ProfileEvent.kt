package dev.lkeeeey.edu.main.presentation.profile.main.viewmodel

sealed interface ProfileEvent {
    data object OnLogOut : ProfileEvent
}