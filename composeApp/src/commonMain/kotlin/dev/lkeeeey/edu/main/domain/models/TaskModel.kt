package dev.lkeeeey.edu.main.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskModel(
    @SerialName("id") val id : Int,
    @SerialName("distribution") val distribution : List<DistributionModel>,
)
