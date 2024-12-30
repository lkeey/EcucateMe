@file:Suppress("PackageDirectoryMismatch")

package dev.lkeeeey.edu.library.data.repository

import com.russhwolf.settings.Settings
import dev.lkeeeey.edu.auth.data.keys.Keys
import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.library.data.network.RemoteLibraryRepository
import dev.lkeeeey.edu.library.domain.LibraryRepository
import dev.lkeeeey.edu.library.domain.models.TeacherModel

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

    fun getAccess() : String {
        return settings.getString(
            key = Keys.ACCESS_TOKEN,
            defaultValue = ""
        )
    }


}