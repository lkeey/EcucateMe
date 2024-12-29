package dev.lkeeeey.edu.main.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TimeTableModel (
    @SerialName("id") val id: Int,
    @SerialName("weekday") val weekDay: Int,
    @SerialName("start") val start: String,
    @SerialName("end") val end: String,
    @SerialName("subject") val name: SubjectModel,
)