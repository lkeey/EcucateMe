package dev.lkeeeey.edu.library.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeacherModel(
    @SerialName("username") val username: String,
    @SerialName("name") val name: String,
    @SerialName("subject") val subject: String,
)
