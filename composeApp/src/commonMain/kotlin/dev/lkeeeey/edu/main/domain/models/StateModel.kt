package dev.lkeeeey.edu.main.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StateModel(
    @SerialName("state") val state : Int
)
