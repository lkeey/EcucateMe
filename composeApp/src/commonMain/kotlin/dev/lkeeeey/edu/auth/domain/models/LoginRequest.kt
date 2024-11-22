package dev.lkeeeey.edu.auth.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest (
    @SerialName("username") val username : String,
    @SerialName("password") val password : String,
)