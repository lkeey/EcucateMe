package dev.lkeeeey.edu.auth.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthLoginDto (
    @SerialName("access") val accessToken: String
)