package dev.lkeeeey.edu.main.presentation.profile.main.viewmodel

import dev.lkeeeey.edu.main.domain.models.ProfileModel

data class ProfileState (
    val isLoading : Boolean = false,
    val errorMessage : String = "",
    val profile : ProfileModel = ProfileModel("Loading", "Loading", "Loading")
)
