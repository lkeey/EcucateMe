package dev.lkeeeey.edu.auth.domain

import dev.lkeeeey.edu.auth.data.database.UserEntity
import dev.lkeeeey.edu.auth.domain.models.AuthResponse
import dev.lkeeeey.edu.auth.domain.models.LoginRequest
import dev.lkeeeey.edu.auth.domain.models.RegisterRequest
import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun loginUser(query: LoginRequest): Result<AuthResponse, DataError.Remote>
    suspend fun registerUser(query: RegisterRequest): Result<Unit, DataError.Remote>

    suspend fun addUser(user: UserEntity): Result<Unit, DataError.Local>
    suspend fun updateUser(user: UserEntity): Result<Unit, DataError.Local>
    fun getUserEntity(): Flow<List<UserEntity>>

}