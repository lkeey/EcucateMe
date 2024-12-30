package dev.lkeeeey.edu.library.domain

import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.library.domain.models.TeacherModel

interface LibraryRepository {

    suspend fun getAllTeachers(
        query: String,
        subject: String,
    ): Result<List<TeacherModel>, DataError.Remote>

}