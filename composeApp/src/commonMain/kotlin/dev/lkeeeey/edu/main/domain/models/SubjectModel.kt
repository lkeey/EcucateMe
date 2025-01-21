package dev.lkeeeey.edu.main.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubjectModel (
    @SerialName("name") val name: String,
    @SerialName("priority") val priority: Boolean,
)