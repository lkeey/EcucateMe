package dev.lkeeeey.edu.library.domain.models

import dev.lkeeeey.edu.library.presentation.tasks.TaskType


data class SolvingTaskModel(
    val id : String = "",
    val content : String = "",
    val type : TaskType = TaskType.NOT_SOLVED,
    val answerModel: AnswerModel = AnswerModel()
)