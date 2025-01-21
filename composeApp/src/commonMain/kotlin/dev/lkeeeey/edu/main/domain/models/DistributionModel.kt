package dev.lkeeeey.edu.main.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DistributionModel(
    @SerialName("start_at") val start: String,
    @SerialName("duration") val durationMin: Int,
)
