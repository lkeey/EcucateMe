package dev.lkeeeey.edu.auth.data.network

import dev.lkeeeey.edu.auth.data.dto.AuthLoginDto
import dev.lkeeeey.edu.auth.domain.models.AuthResponse
import dev.lkeeeey.edu.auth.domain.models.LoginRequest
import dev.lkeeeey.edu.auth.domain.models.RegisterRequest
import dev.lkeeeey.edu.core.domain.DataError

interface RemoteAuthDataSource {
    suspend fun loginUser(
        query: LoginRequest
    ): dev.lkeeeey.edu.core.domain.Result<AuthLoginDto, DataError.Remote>

    suspend fun registerUser(
        query: RegisterRequest
    ): dev.lkeeeey.edu.core.domain.Result<Unit, DataError.Remote>

}
