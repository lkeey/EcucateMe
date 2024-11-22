package dev.lkeeeey.edu.auth.domain.models

data class LoginRequest (
    val username : String,
    val password : String,
)