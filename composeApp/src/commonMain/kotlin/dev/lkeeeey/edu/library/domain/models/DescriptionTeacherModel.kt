package dev.lkeeeey.edu.library.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DescriptionTeacherModel(
    @SerialName("username") val username: String,
    @SerialName("name") val name: String,
    @SerialName("bio") val bio: String,
//    @SerialName("avatar_url") val logo: String,
    @SerialName("subject") val subject: String,
)
