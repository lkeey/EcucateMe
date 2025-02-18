package dev.lkeeeey.edu.library.presentation.tasks

import dev.lkeeeey.edu.library.domain.models.AnswerModel

sealed interface FullTaskEvent {
    data object OnLoadFullModel : FullTaskEvent
    data class OnAnswer(val id: String) : FullTaskEvent
    data class OnUpdateAnswer(val id: String, val answer: AnswerModel) : FullTaskEvent
}
