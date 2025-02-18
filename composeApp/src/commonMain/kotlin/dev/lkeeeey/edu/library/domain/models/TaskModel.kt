package dev.lkeeeey.edu.library.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskModel(
    @SerialName("id") val id : String = "",
    @SerialName("content") val content : String = "",
)
