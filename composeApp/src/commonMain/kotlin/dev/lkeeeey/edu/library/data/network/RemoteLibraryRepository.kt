package dev.lkeeeey.edu.library.data.network

import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.library.domain.models.AnswerModel
import dev.lkeeeey.edu.library.domain.models.BlockTaskModel
import dev.lkeeeey.edu.library.domain.models.CheckAnswerModel
import dev.lkeeeey.edu.library.domain.models.DescriptionTeacherModel
import dev.lkeeeey.edu.library.domain.models.FullBlockModel
import dev.lkeeeey.edu.library.domain.models.SelectTeacherModel
import dev.lkeeeey.edu.library.domain.models.TeacherModel
import dev.lkeeeey.edu.main.domain.models.SelectedTeacherModel

interface RemoteLibraryRepository {

    suspend fun getAllTeachers(
        access: String,
        query: String,
        subject: String
    ): Result<List<TeacherModel>, DataError.Remote>

    suspend fun getMyTeachers(
        access: String,
    ): Result<List<SelectedTeacherModel>, DataError.Remote>

    suspend fun getTeacherDescription(
        access: String,
        username: String
    ): Result<DescriptionTeacherModel, DataError.Remote>

    suspend fun selectTeacher(
        access: String,
        teacher: SelectTeacherModel
    ): Result<Unit, DataError.Remote>

    suspend fun unselectTeacher(
        access: String,
        username: String
    ): Result<Unit, DataError.Remote>

    suspend fun searchTaskBlocks(
        access: String,
        title: String,
        subject: String,
    ): Result<List<BlockTaskModel>, DataError.Remote>

    suspend fun getFullTaskBlock(
        access: String,
        id: String,
    ): Result<FullBlockModel, DataError.Remote>

    suspend fun checkAnswer(
        access: String,
        id: String,
        answer: AnswerModel
    ): Result<CheckAnswerModel, DataError.Remote>
}