package dev.lkeeeey.edu.core.presentation

import androidx.compose.runtime.Composable
import dev.lkeeeey.edu.core.domain.DataError
import ecucateme.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource


sealed interface UiText {
    data class DynamicString(val value: String): UiText
    class StringResourceId(
        val id: StringResource,
        val args: Array<Any> = arrayOf()
    ): UiText

    @Composable
    fun asString(): String {
        return when(this) {
            is DynamicString -> value
            is StringResourceId -> stringResource(resource = id, formatArgs = args)
        }
    }
}

fun DataError.toStr(): String {
    val stringRes = when(this) {
        DataError.Local.DISK_FULL -> "error_disk_full"
        DataError.Local.UNKNOWN -> "error_unknown"
        DataError.Remote.REQUEST_TIMEOUT -> "error_request_timeout"
        DataError.Remote.TOO_MANY_REQUESTS -> "error_too_many_requests"
        DataError.Remote.NO_INTERNET -> "error_no_internet"
        DataError.Remote.SERVER -> "error_unknown"
        DataError.Remote.SERIALIZATION -> "error_serialization"
        DataError.Remote.UNKNOWN -> "error_unknown"
    }

    return stringRes
}