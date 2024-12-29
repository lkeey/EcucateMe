package dev.lkeeeey.edu.main.domain.models

import kotlinx.serialization.SerialName

data class SubjectPresModel(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("priority") val priority: Boolean,
)
