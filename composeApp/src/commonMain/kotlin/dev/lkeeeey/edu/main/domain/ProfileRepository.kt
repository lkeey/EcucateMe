package dev.lkeeeey.edu.main.domain

import dev.lkeeeey.edu.auth.data.dto.AuthLoginDto
import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.main.domain.models.ProfileModel
import dev.lkeeeey.edu.main.domain.models.SubjectPresModel
import dev.lkeeeey.edu.main.domain.models.SubjectSchedule
import dev.lkeeeey.edu.main.domain.models.TimeTableModel

interface ProfileRepository {

    suspend fun getTimeTable(): Result<List<TimeTableModel>, DataError.Remote>

    suspend fun refreshToken(): Result<AuthLoginDto, DataError.Remote>

    suspend fun getSubjects(): Result<List<SubjectPresModel>, DataError.Remote>

    suspend fun createSubject(
        subject: SubjectPresModel
    ): Result<SubjectPresModel, DataError.Remote>

    suspend fun updateSubject(
        subject: SubjectPresModel
    ): Result<Unit, DataError.Remote>

    suspend fun deleteSubjectFromSchedule(
        deletedId: Int
    ): Result<Unit, DataError.Remote>

    suspend fun addSubjectToSchedule(
        subject: SubjectSchedule
    ): Result<SubjectSchedule, DataError.Remote>

    suspend fun getTeacherProfile() : Result<ProfileModel, DataError.Remote>

}
