package dev.lkeeeey.edu.auth.data.repository

import dev.lkeeeey.edu.auth.data.dto.AuthLoginDto
import dev.lkeeeey.edu.auth.data.network.RemoteAuthDataSource
import dev.lkeeeey.edu.auth.domain.AuthRepository
import dev.lkeeeey.edu.auth.domain.models.AuthResponse
import dev.lkeeeey.edu.auth.domain.models.LoginRequest
import dev.lkeeeey.edu.auth.domain.models.RegisterRequest
import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.core.domain.map

class AuthRepositoryImpl (
    private val remoteAuthDataSource: RemoteAuthDataSource,
) : AuthRepository {

    override suspend fun loginUser(query: LoginRequest): Result<AuthResponse, DataError.Remote> {
        return remoteAuthDataSource
            .loginUser(query)
            .map {
                it.toAuthResponse()
            }
    }

    override suspend fun registerUser(query: RegisterRequest): Result<Unit, DataError.Remote> {
        return remoteAuthDataSource
            .registerUser(query)
    }

}

private fun AuthLoginDto.toAuthResponse() : AuthResponse {
    return AuthResponse(accessToken)
}


