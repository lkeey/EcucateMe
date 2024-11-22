package dev.lkeeeey.edu.auth.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest (
    @SerialName("username") val username : String,
    @SerialName("name") val name : String,
    @SerialName("password") val password : String,
    @SerialName("account_type") val accountType : String,
)