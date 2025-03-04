package dev.lkeeeey.edu.main.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskModel(
    @SerialName("id") val id : Int,
    @SerialName("content") val content : String,
    @SerialName("subject_name") val subject : String,
    @SerialName("deadline") val deadline : String,

    @SerialName("distribution") val distribution : List<DistributionModel>,
)
