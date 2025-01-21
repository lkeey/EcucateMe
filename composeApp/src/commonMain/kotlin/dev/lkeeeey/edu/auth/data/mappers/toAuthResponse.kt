package dev.lkeeeey.edu.auth.data.mappers

import dev.lkeeeey.edu.auth.data.dto.AuthLoginDto
import dev.lkeeeey.edu.auth.domain.models.AuthResponse

fun AuthLoginDto.toAuthResponse() : AuthResponse {
//    val tokens = accessToken.split(" ")
    return AuthResponse(access = accessToken)
}
