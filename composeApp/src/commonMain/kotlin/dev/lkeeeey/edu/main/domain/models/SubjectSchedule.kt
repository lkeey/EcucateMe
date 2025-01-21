package dev.lkeeeey.edu.main.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubjectSchedule(
    @SerialName("subject_id") val id: Int,
    @SerialName("weekday") val weekday: Int,
    @SerialName("start") val start: String,
    @SerialName("end") val end: String,
)
