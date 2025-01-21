package dev.lkeeeey.edu.main.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateTaskModel (
    @SerialName("subject_id") val subjectId: Int,
    @SerialName("deadline") val deadline: String,
    @SerialName("content") val content: String,
    @SerialName("execution_time") val executionTime: Int,
)
