package dev.lkeeeey.edu.library.presentation.tasks

import dev.lkeeeey.edu.library.domain.models.AnswerModel
import dev.lkeeeey.edu.library.domain.models.FullBlockModel
import dev.lkeeeey.edu.library.domain.models.SolvingTaskModel

data class FullTaskState(
    val block: FullBlockModel = FullBlockModel(),
    val answerModel: AnswerModel = AnswerModel(),
    val solvingTasks: List<SolvingTaskModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
)
