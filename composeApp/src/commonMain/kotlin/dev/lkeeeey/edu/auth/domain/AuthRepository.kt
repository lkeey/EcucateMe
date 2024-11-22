package dev.lkeeeey.edu.auth.domain

import dev.lkeeeey.edu.auth.domain.models.AuthResponse
import dev.lkeeeey.edu.auth.domain.models.LoginRequest
import dev.lkeeeey.edu.auth.domain.models.RegisterRequest
import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result

interface AuthRepository {

    suspend fun loginUser(query: LoginRequest): Result<AuthResponse, DataError.Remote>
    suspend fun registerUser(query: RegisterRequest): Result<Unit, DataError.Remote>

}
