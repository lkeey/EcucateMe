package dev.lkeeeey.edu.main.presentation.profile.teachers.viewmodel

import dev.lkeeeey.edu.main.domain.models.SelectedTeacherModel

data class MyTeachersState(
    val myTeachers: List<SelectedTeacherModel> = emptyList()
)
