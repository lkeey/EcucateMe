package dev.lkeeeey.edu.library.data.network

import dev.lkeeeey.edu.core.data.safeCall
import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.library.domain.models.TeacherModel
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://me-educate.ru/api"

class RemoteLibraryRepositoryImpl(
    private val httpClient: HttpClient
) : RemoteLibraryRepository {

    override suspend fun getAllTeachers(
        access: String,
        query: String,
        subject: String
    ): Result<List<TeacherModel>, DataError.Remote> {
        return safeCall<List<TeacherModel>> {
            httpClient.get(
                urlString = "$BASE_URL/schedule/teacher/all"
            ) {
                bearerAuth(access)
                parameter("query", query)
                parameter("subject", subject)
            }
        }
    }

}
