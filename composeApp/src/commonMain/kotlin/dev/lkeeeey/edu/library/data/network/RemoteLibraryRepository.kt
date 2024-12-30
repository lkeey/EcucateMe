package dev.lkeeeey.edu.library.data.network

import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.library.domain.models.DescriptionTeacherModel
import dev.lkeeeey.edu.library.domain.models.TeacherModel

interface RemoteLibraryRepository {

    suspend fun getAllTeachers(
        access: String,
        query: String,
        subject: String
    ): Result<List<TeacherModel>, DataError.Remote>

    suspend fun getTeacherDescription(
        access: String,
        username: String
    ): Result<DescriptionTeacherModel, DataError.Remote>


}