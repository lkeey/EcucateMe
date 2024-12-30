package dev.lkeeeey.edu.main.data.network

import dev.lkeeeey.edu.auth.data.dto.AuthLoginDto
import dev.lkeeeey.edu.auth.domain.models.LoginRequest
import dev.lkeeeey.edu.core.data.safeCall
import dev.lkeeeey.edu.core.data.safeCallWithCookies
import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.main.domain.models.SubjectPresModel
import dev.lkeeeey.edu.main.domain.models.SubjectSchedule
import dev.lkeeeey.edu.main.domain.models.TimeTableModel
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody


private const val BASE_URL = "https://me-educate.ru/api"

class RemoteProfileDataSourceImpl(
    private val httpClient: HttpClient
): RemoteProfileDataSource {

    override suspend fun getTimeTable(access: String): Result<List<TimeTableModel>, DataError.Remote> {
        return safeCall<List<TimeTableModel>> {
            httpClient.get(
                urlString = "$BASE_URL/schedule"
            ) {
                bearerAuth(access)
            }
        }
    }

    override suspend fun updateTimeTable(
        access: String,
        schedule: List<TimeTableModel>
    ): Result<List<TimeTableModel>, DataError.Remote> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshToken(
        access: LoginRequest,
        saveCookies: (String) -> Unit
    ): Result<AuthLoginDto, DataError.Remote> {

        return safeCallWithCookies<AuthLoginDto> (
            saveToLocalDB = saveCookies
        ) {
            httpClient.post(
                urlString = "$BASE_URL/auth/login"
            ) {
                setBody(
                    access
                )
            }
        }
    }

    override suspend fun createSubject(
        access: String,
        subject: SubjectPresModel
    ): Result<SubjectPresModel, DataError.Remote> {
        return safeCall<SubjectPresModel> {
            httpClient.post(
                urlString = "$BASE_URL/schedule/subject"
            ) {
                setBody(
                    subject
                )
                bearerAuth(
                    access
                )
            }
        }
    }

    override suspend fun getSubjects(access: String): Result<List<SubjectPresModel>, DataError.Remote> {
        return safeCall<List<SubjectPresModel>> {
            httpClient.get(
                urlString = "$BASE_URL/schedule/subject"
            ) {
                bearerAuth(
                    access
                )
            }
        }
    }

    override suspend fun updateSubject(
        access: String,
        subject: SubjectPresModel
    ): Result<Unit, DataError.Remote> {
        return safeCall<Unit> {
            httpClient.patch(
                urlString = "$BASE_URL/schedule/subject/${subject.id}"
            ) {
                setBody(
                    subject
                )
                bearerAuth(
                    access
                )
            }
        }
    }

    override suspend fun deleteSubjectFromSchedule(
        access: String,
        deletedId: Int
    ): Result<Unit, DataError.Remote> {
        return safeCall<Unit> {
            httpClient.delete(
                urlString = "$BASE_URL/schedule/$deletedId"
            ) {
                bearerAuth(
                    access
                )
            }
        }
    }

    override suspend fun addSubjectToSchedule(
        access: String,
        subject: SubjectSchedule
    ): Result<SubjectSchedule, DataError.Remote> {
        return safeCall<SubjectSchedule> {
            httpClient.post(
                urlString = "$BASE_URL/schedule"
            ) {
                bearerAuth(
                    access
                )
                setBody(
                    subject
                )
            }
        }
    }

}
