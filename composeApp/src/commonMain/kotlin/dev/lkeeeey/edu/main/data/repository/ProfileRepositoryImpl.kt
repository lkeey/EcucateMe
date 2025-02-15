package dev.lkeeeey.edu.main.data.repository

import com.russhwolf.settings.Settings
import dev.lkeeeey.edu.auth.data.dto.AuthLoginDto
import dev.lkeeeey.edu.auth.data.keys.Keys
import dev.lkeeeey.edu.auth.domain.AuthRepository
import dev.lkeeeey.edu.auth.domain.models.LoginRequest
import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.main.data.network.RemoteProfileDataSource
import dev.lkeeeey.edu.main.domain.ProfileRepository
import dev.lkeeeey.edu.main.domain.models.CreateTaskModel
import dev.lkeeeey.edu.main.domain.models.ProfileModel
import dev.lkeeeey.edu.main.domain.models.SubjectPresModel
import dev.lkeeeey.edu.main.domain.models.SubjectSchedule
import dev.lkeeeey.edu.main.domain.models.TaskModel
import dev.lkeeeey.edu.main.domain.models.TimeTableModel

class ProfileRepositoryImpl (
    private val remoteProfileDataSource: RemoteProfileDataSource,
    private val authRepository: AuthRepository
) : ProfileRepository {

    private val settings = Settings()

    override suspend fun getDistributionTasks(): Result<List<TaskModel>, DataError.Remote> {
        return remoteProfileDataSource
            .getDistributionTasks(
                getAccess()
            )
    }

    override suspend fun getTimeTable(): Result<List<TimeTableModel>, DataError.Remote> {

        return remoteProfileDataSource
            .getTimeTable(
                getAccess()
            )
    }

    override suspend fun refreshToken(): Result<AuthLoginDto, DataError.Remote> {
        return remoteProfileDataSource.refreshToken(
            access = LoginRequest(
                username = settings.getString(
                    key = Keys.LOGIN,
                    defaultValue = ""
                ),
                password = settings.getString(
                    key = Keys.PASSWORD,
                    defaultValue = ""
                ),
            ),
            saveCookies = {
                authRepository.updateRefreshToken(it)
            }
        )
    }

    override suspend fun getSubjects(): Result<List<SubjectPresModel>, DataError.Remote> {
        return remoteProfileDataSource.getSubjects(
            access = getAccess()
        )
    }

    override suspend fun createSubject(subject: SubjectPresModel): Result<SubjectPresModel, DataError.Remote> {
        return remoteProfileDataSource.createSubject(
            access = getAccess(),
            subject = subject
        )
    }

    override suspend fun createDistributedTask(task: CreateTaskModel): Result<CreateTaskModel, DataError.Remote> {
        return remoteProfileDataSource.createDistributedTask(
            username = getUsername(),
            access = getAccess(),
            task = task
        )
    }

    override suspend fun updateSubject(subject: SubjectPresModel): Result<Unit, DataError.Remote> {
        return remoteProfileDataSource.updateSubject(
            access = getAccess(),
            subject = subject
        )
    }

    override suspend fun deleteSubjectFromSchedule(deletedId: Int): Result<Unit, DataError.Remote> {
        return remoteProfileDataSource.deleteSubjectFromSchedule(
            access = getAccess(),
            deletedId = deletedId
        )
    }

    override suspend fun addSubjectToSchedule(subject: SubjectSchedule): Result<SubjectSchedule, DataError.Remote> {
        return remoteProfileDataSource.addSubjectToSchedule(
            access = getAccess(),
            subject = subject
        )
    }

    override suspend fun getTeacherProfile(): Result<ProfileModel, DataError.Remote> {
        return remoteProfileDataSource.getTeacherProfile(
            access = getAccess(),
            username = getUsername()
        )
    }

    private fun getUsername() : String {
        return settings.getString(
            key = Keys.MY_USERNAME,
            defaultValue = ""
        )
    }

    private fun getAccess() : String {
        return settings.getString(
            key = Keys.ACCESS_TOKEN,
            defaultValue = ""
        )
    }

    fun getRefresh() : String {
        return settings.getString(
            key = Keys.REFRESH_TOKEN,
            defaultValue = ""
        )
    }


}
