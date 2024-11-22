package dev.lkeeeey.edu.core.data

import dev.lkeeeey.edu.core.domain.DataError
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): dev.lkeeeey.edu.core.domain.Result<T, DataError.Remote> {
    val response = try {
        execute()
    } catch(e: SocketTimeoutException) {
        return dev.lkeeeey.edu.core.domain.Result.Error(DataError.Remote.REQUEST_TIMEOUT)
    } catch(e: UnresolvedAddressException) {
        return dev.lkeeeey.edu.core.domain.Result.Error(DataError.Remote.NO_INTERNET)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return dev.lkeeeey.edu.core.domain.Result.Error(DataError.Remote.UNKNOWN)
    }

    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): dev.lkeeeey.edu.core.domain.Result<T, DataError.Remote> {
    return when(response.status.value) {
        in 200..299 -> {
            try {
                dev.lkeeeey.edu.core.domain.Result.Success(response.body<T>())
            } catch(e: NoTransformationFoundException) {
                dev.lkeeeey.edu.core.domain.Result.Error(DataError.Remote.SERIALIZATION)
            }
        }
        408 -> dev.lkeeeey.edu.core.domain.Result.Error(DataError.Remote.REQUEST_TIMEOUT)
        429 -> dev.lkeeeey.edu.core.domain.Result.Error(DataError.Remote.TOO_MANY_REQUESTS)
        in 500..599 -> dev.lkeeeey.edu.core.domain.Result.Error(DataError.Remote.SERVER)
        else -> dev.lkeeeey.edu.core.domain.Result.Error(DataError.Remote.UNKNOWN)
    }
}