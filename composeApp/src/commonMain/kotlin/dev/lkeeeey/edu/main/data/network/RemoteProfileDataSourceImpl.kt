package dev.lkeeeey.edu.main.data.network

import dev.lkeeeey.edu.core.data.safeCall
import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import dev.lkeeeey.edu.main.domain.models.TimeTableModel
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get


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

}
