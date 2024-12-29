package dev.lkeeeey.edu.main.presentation.profile.subjects.viewmodel

import dev.lkeeeey.edu.main.domain.models.SubjectPresModel

sealed interface SubjectsEvent {
    data object OnCreateSubject : SubjectsEvent
    data class OnUpdatePriority(val subject: SubjectPresModel) : SubjectsEvent
    data class OnUpdateSubjectName(val name: String) : SubjectsEvent
}