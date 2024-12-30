package dev.lkeeeey.edu.library.presentation.teachers.viewmodel

import dev.lkeeeey.edu.library.domain.models.TeacherModel

data class AllTeachersState (
    val teachers : List<TeacherModel> = emptyList(),
    val selectedUsername : String = "",
    val query : String = "",
    val subject : String = "",
)