package dev.lkeeeey.edu.auth.domain.models

data class RegisterRequest (
    val username : String,
    val name : String,
    val password : String,
    val accountType : String,
)