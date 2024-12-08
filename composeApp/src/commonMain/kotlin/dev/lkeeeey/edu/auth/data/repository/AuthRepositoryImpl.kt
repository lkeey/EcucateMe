package dev.lkeeeey.edu.auth.data.repository

import androidx.sqlite.SQLiteException
import com.russhwolf.settings.Settings
import dev.lkeeeey.edu.auth.data.database.UserDao
import dev.lkeeeey.edu.auth.data.database.UserEntity
import dev.lkeeeey.edu.auth.data.keys.Keys
import dev.lkeeeey.edu.auth.data.mappers.toAuthResponse
import dev.lkeeeey.edu.auth.data.network.RemoteAuthDataSource
import dev.lkeeeey.edu.auth.domain.AuthRepository
import dev.lkeeeey.edu.auth.domain.models.AuthResponse
import dev.lkeeeey.edu.auth.domain.models.LoginRequest
import dev.lkeeeey.edu.auth.domain.models.RegisterRequest
import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.core.domain.map
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl (
    private val remoteAuthDataSource: RemoteAuthDataSource,
    private val userDao: UserDao,
) : AuthRepository {

    private val settings = Settings()

    override suspend fun loginUser(
        query: LoginRequest
    ): Result<AuthResponse, DataError.Remote> {
        return remoteAuthDataSource
            .loginUser(
                saveCookies = { refresh->
                    updateRefreshToken(
                        refresh = refresh
                    )
                },
                query = query
            )
            .map {
                it.toAuthResponse()
            }
    }

    override suspend fun registerUser(query: RegisterRequest): Result<Unit, DataError.Remote> {
        return remoteAuthDataSource
            .registerUser(query)
    }

    override suspend fun addUser(user: UserEntity): Result<Unit, DataError.Local> {
        return try {
            userDao.upsertUser(user = user)
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun updateUser(user: UserEntity): Result<Unit, DataError.Local> {
        return try {
            userDao.updateUser(user = user)
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override fun getUserEntity(): Flow<List<UserEntity>> {
        return userDao
            .getUser()

    }

    override suspend fun deleteAllUsers(): Result<Unit, DataError.Local> {
        return try {
            userDao
                .deleteAllUsers()
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override fun updateRefreshToken(refresh: String): Result<Unit, DataError.Local> {
        return try {
            settings.putString(
                key = Keys.REFRESH_TOKEN,
                value = refresh
            )
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override fun updateAccessToken(access: String): Result<Unit, DataError.Local> {
        return try {
            settings.putString(
                key = Keys.ACCESS_TOKEN,
                value = access
            )
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override fun updateAuthenticated(isAuthenticated: Boolean): Result<Unit, DataError.Local> {
        return try {
            settings.putBoolean(
                key = Keys.IS_LOGIN,
                value = isAuthenticated
            )
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

}




