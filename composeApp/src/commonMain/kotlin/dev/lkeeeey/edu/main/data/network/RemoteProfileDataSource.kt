package dev.lkeeeey.edu.main.data.network

import dev.lkeeeey.edu.auth.data.dto.AuthLoginDto
import dev.lkeeeey.edu.auth.domain.models.LoginRequest
import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.main.domain.models.ProfileModel
import dev.lkeeeey.edu.main.domain.models.SubjectPresModel
import dev.lkeeeey.edu.main.domain.models.SubjectSchedule
import dev.lkeeeey.edu.main.domain.models.TaskModel
import dev.lkeeeey.edu.main.domain.models.TimeTableModel

interface RemoteProfileDataSource {
    suspend fun getTimeTable(
        access: String
    ): Result<List<TimeTableModel>, DataError.Remote>

    suspend fun getDistributionTasks(
        access: String
    ): Result<List<TaskModel>, DataError.Remote>


    suspend fun updateTimeTable(
        access: String,
        schedule: List<TimeTableModel>
    ): Result<List<TimeTableModel>, DataError.Remote>

    suspend fun refreshToken(
        access: LoginRequest,
        saveCookies: (String) -> Unit
    ): Result<AuthLoginDto, DataError.Remote>

    suspend fun createSubject(
        access: String,
        subject: SubjectPresModel
    ): Result<SubjectPresModel, DataError.Remote>

    suspend fun getSubjects(
        access: String
    ): Result<List<SubjectPresModel>, DataError.Remote>

    suspend fun updateSubject(
        access: String,
        subject: SubjectPresModel
    ): Result<Unit, DataError.Remote>

    suspend fun deleteSubjectFromSchedule(
        access: String,
        deletedId: Int
    ): Result<Unit, DataError.Remote>

    suspend fun addSubjectToSchedule(
        access: String,
        subject: SubjectSchedule
    ): Result<SubjectSchedule, DataError.Remote>

    suspend fun getTeacherProfile(
        access: String,
        username: String
    ) : Result<ProfileModel, DataError.Remote>

}
