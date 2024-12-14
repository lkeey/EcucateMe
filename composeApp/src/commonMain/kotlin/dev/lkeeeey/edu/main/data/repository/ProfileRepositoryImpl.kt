package dev.lkeeeey.edu.main.data.repository

import com.russhwolf.settings.Settings
import dev.lkeeeey.edu.auth.data.dto.AuthLoginDto
import dev.lkeeeey.edu.auth.data.keys.Keys
import dev.lkeeeey.edu.auth.domain.AuthRepository
import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.core.domain.onSuccess
import dev.lkeeeey.edu.main.data.network.RemoteProfileDataSource
import dev.lkeeeey.edu.main.domain.ProfileRepository
import dev.lkeeeey.edu.main.domain.models.TimeTableModel

class ProfileRepositoryImpl (
    private val remoteProfileDataSource: RemoteProfileDataSource,
    private val authRepository: AuthRepository
) : ProfileRepository {

    private val settings = Settings()

    override suspend fun getTimeTable(): Result<List<TimeTableModel>, DataError.Remote> {

        return remoteProfileDataSource
            .getTimeTable(
                getAccess()
            )
    }

    override suspend fun refreshToken(): Result<AuthLoginDto, DataError.Remote> {
        return remoteProfileDataSource.refreshToken(
            getAccess()
        ) {
            authRepository.updateRefreshToken(it)
        }
    }

    fun getAccess() : String {
        return settings.getString(
            key = Keys.ACCESS_TOKEN,
            defaultValue = ""
        )
    }


}
