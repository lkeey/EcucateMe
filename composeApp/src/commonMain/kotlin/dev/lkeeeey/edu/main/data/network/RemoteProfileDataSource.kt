package dev.lkeeeey.edu.main.data.network

import dev.lkeeeey.edu.auth.data.dto.AuthLoginDto
import dev.lkeeeey.edu.auth.domain.models.LoginRequest
import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.main.domain.models.TimeTableModel

interface RemoteProfileDataSource {
    suspend fun getTimeTable(
        access: String
    ): Result<List<TimeTableModel>, DataError.Remote>

    suspend fun updateTimeTable(
        access: String
    ): Result<List<TimeTableModel>, DataError.Remote>


    suspend fun refreshToken(
        access: LoginRequest,
        saveCookies: (String) -> Unit
    ): Result<AuthLoginDto, DataError.Remote>
}
