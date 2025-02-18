package dev.lkeeeey.edu.library.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlockTaskModel(
    @SerialName("id") val id : String = "",
    @SerialName("title") val title : String = "",
    @SerialName("subject") val subject : String = "",
)
