package dev.lkeeeey.edu.main.presentation.profile.subjects.viewmodel

import dev.lkeeeey.edu.main.domain.models.SubjectPresModel

data class SubjectsState (
    val subjects: List<SubjectPresModel> = emptyList(),
    val subjectName: String = "",
)