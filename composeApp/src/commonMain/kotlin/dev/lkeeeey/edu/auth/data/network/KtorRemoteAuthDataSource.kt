package dev.lkeeeey.edu.auth.data.network

import dev.lkeeeey.edu.auth.data.dto.AuthLoginDto
import dev.lkeeeey.edu.auth.domain.models.LoginRequest
import dev.lkeeeey.edu.auth.domain.models.RegisterRequest
import dev.lkeeeey.edu.core.data.responseToResult
import dev.lkeeeey.edu.core.data.safeCall
import dev.lkeeeey.edu.core.domain.DataError
import dev.lkeeeey.edu.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.setCookie
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

private const val BASE_URL = "https://me-educate.ru/api"

class KtorRemoteBookDataSource(
    private val httpClient: HttpClient
): RemoteAuthDataSource {

//    override suspend fun loginUser(
//        query: LoginRequest
//    ): Result<AuthLoginDto, DataError.Remote> {
//        return safeCall<AuthLoginDto> {
//            httpClient.post(
//                urlString = "$BASE_URL/auth/login"
//            ) {
//                setBody(
//                    query
//                )
//            }
//        }
//    }

    override suspend fun loginUser(
        query: LoginRequest
    ): Result<AuthLoginDto, DataError.Remote> {
        val response = try {
            httpClient.post(
                urlString = "$BASE_URL/auth/login"
            ) {
                setBody(
                    query
                )
            }
        } catch(e: SocketTimeoutException) {
            return Result.Error(DataError.Remote.REQUEST_TIMEOUT)
        } catch(e: UnresolvedAddressException) {
            return Result.Error(DataError.Remote.NO_INTERNET)
        } catch (e: Exception) {
            println("erroooooor- " + e.message)
            coroutineContext.ensureActive()
            return Result.Error(DataError.Remote.UNKNOWN)
        }

        // TODO save into local database

        println("find cookies - ${response.setCookie()}")

        return responseToResult(response)
    }

    override suspend fun registerUser(
        query: RegisterRequest
    ): Result<Unit, DataError.Remote> {
        return safeCall<Unit> {
            httpClient.post(
                urlString = "$BASE_URL/auth/register"
            ) {
                setBody(
                    query
                )
            }
        }

    }

}
