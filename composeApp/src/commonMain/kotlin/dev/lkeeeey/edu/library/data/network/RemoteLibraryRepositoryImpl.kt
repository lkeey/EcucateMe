package dev.lkeeeey.edu.library.data.network

import dev.lkeeeey.edu.core.data.safeCall
import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.library.domain.models.DescriptionTeacherModel
import dev.lkeeeey.edu.library.domain.models.SelectTeacherModel
import dev.lkeeeey.edu.library.domain.models.TeacherModel
import dev.lkeeeey.edu.main.domain.models.SelectedTeacherModel
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody

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

    override suspend fun getMyTeachers(access: String): Result<List<SelectedTeacherModel>, DataError.Remote> {
        return safeCall<List<SelectedTeacherModel>> {
            httpClient.get(
                urlString = "$BASE_URL/schedule/teacher"
            ) {
                bearerAuth(access)
            }
        }
    }

    override suspend fun getTeacherDescription(
        access: String,
        username: String
    ): Result<DescriptionTeacherModel, DataError.Remote> {
        return safeCall<DescriptionTeacherModel> {
            httpClient.get(
                urlString = "$BASE_URL/auth/user/$username"
            ) {
                bearerAuth(access)
            }
        }
    }

    override suspend fun selectTeacher(
        access: String,
        teacher: SelectTeacherModel
    ): Result<Unit, DataError.Remote> {
        return safeCall<Unit> {
            httpClient.post(
                urlString = "$BASE_URL/schedule/teacher"
            ) {
                bearerAuth(access)
                setBody(teacher)
            }
        }
    }

    override suspend fun unselectTeacher(
        access: String,
        username: String
    ): Result<Unit, DataError.Remote> {
        return safeCall<Unit> {
            httpClient.delete(
                urlString = "$BASE_URL/schedule/teacher/$username"
            ) {
                bearerAuth(access)
            }
        }
    }

}
