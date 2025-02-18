package dev.lkeeeey.edu.library.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnswerModel(
    @SerialName("answer") val answer : String = ""
)
