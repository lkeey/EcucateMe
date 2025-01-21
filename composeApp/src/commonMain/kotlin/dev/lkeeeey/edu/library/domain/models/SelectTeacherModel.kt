package dev.lkeeeey.edu.library.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SelectTeacherModel(
    @SerialName("username") val username: String,
)
