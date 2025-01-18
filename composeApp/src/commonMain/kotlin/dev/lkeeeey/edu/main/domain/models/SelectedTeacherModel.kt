package dev.lkeeeey.edu.main.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SelectedTeacherModel(
    @SerialName("username") val username: String,
    @SerialName("name") val name: String,
    @SerialName("subject") val subject: String,
)