package dev.lkeeeey.edu.library.domain

import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.library.domain.models.DescriptionTeacherModel
import dev.lkeeeey.edu.library.domain.models.TeacherModel
import dev.lkeeeey.edu.main.domain.models.SelectedTeacherModel

interface LibraryRepository {

    suspend fun getAllTeachers(
        query: String,
        subject: String,
    ): Result<List<TeacherModel>, DataError.Remote>

    suspend fun getTeacherDescription(
        username: String
    ): Result<DescriptionTeacherModel, DataError.Remote>

    suspend fun getSelectedTeachers(): Result<List<SelectedTeacherModel>, DataError.Remote>

    suspend fun selectTeacher(
        teacher: SelectedTeacherModel
    ): Result<Unit, DataError.Remote>

    suspend fun unselectTeacher(
        username: String
    ): Result<Unit, DataError.Remote>

}
