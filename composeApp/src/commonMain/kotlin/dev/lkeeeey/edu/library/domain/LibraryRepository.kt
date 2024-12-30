package dev.lkeeeey.edu.library.domain

import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.main.domain.models.SubjectPresModel

interface LibraryRepository {
    suspend fun createSubject(
        subject: SubjectPresModel
    ): Result<SubjectPresModel, DataError.Remote>

}