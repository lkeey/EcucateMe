package dev.lkeeeey.edu.library.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FullBlockModel (
    @SerialName("id") val id : String = "",
    @SerialName("title") val title : String = "",
    @SerialName("subject") val subject : String = "",
    @SerialName("owner") val author : AuthorBlockModel = AuthorBlockModel(),
    @SerialName("tasks") val tasks : List<TaskModel> = emptyList(),
)