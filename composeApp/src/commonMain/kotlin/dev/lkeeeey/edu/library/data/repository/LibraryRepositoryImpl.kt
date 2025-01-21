package dev.lkeeeey.edu.library.data.repository

import com.russhwolf.settings.Settings
import dev.lkeeeey.edu.auth.data.keys.Keys
import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.library.data.network.RemoteLibraryRepository
import dev.lkeeeey.edu.library.domain.LibraryRepository
import dev.lkeeeey.edu.library.domain.models.DescriptionTeacherModel
import dev.lkeeeey.edu.library.domain.models.SelectTeacherModel
import dev.lkeeeey.edu.library.domain.models.TeacherModel
import dev.lkeeeey.edu.main.domain.models.SelectedTeacherModel

class LibraryRepositoryImpl (
    private val remoteLibraryRepository: RemoteLibraryRepository
) : LibraryRepository {

    private val settings = Settings()

    override suspend fun getAllTeachers(
        query: String,
        subject: String
    ): Result<List<TeacherModel>, DataError.Remote> {
        return remoteLibraryRepository.getAllTeachers(
            access = getAccess(),
            query = query,
            subject = subject
        )
    }

    override suspend fun getTeacherDescription(username: String): Result<DescriptionTeacherModel, DataError.Remote> {
        return remoteLibraryRepository.getTeacherDescription(
            access = getAccess(),
            username = username
        )
    }

    override suspend fun getSelectedTeachers(): Result<List<SelectedTeacherModel>, DataError.Remote> {
        return remoteLibraryRepository.getMyTeachers(
            access = getAccess()
        )
    }

    override suspend fun selectTeacher(teacher: SelectTeacherModel): Result<Unit, DataError.Remote> {
        return remoteLibraryRepository.selectTeacher(
            access = getAccess(),
            teacher = teacher
        )
    }

    override suspend fun unselectTeacher(username: String): Result<Unit, DataError.Remote> {
        return remoteLibraryRepository.unselectTeacher(
            access = getAccess(),
            username = username
        )
    }

    private fun getAccess() : String {
        return settings.getString(
            key = Keys.ACCESS_TOKEN,
            defaultValue = ""
        )
    }


}