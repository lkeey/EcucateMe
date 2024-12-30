package dev.lkeeeey.edu.profile.presentation.viewmodel

sealed interface ProfileEvent {
    data object OnLogOut : ProfileEvent
}