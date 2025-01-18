package dev.lkeeeey.edu.main.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileModel(
    @SerialName("name") val name : String,
    @SerialName("username") val username : String,
    @SerialName("avatar_url") val profileLogoUrl : String,
)
