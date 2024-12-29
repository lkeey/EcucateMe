package dev.lkeeeey.edu.main.presentation.profile.subjects.viewmodel

sealed interface SubjectsEvent {
    data object OnCreateSubject : SubjectsEvent
    data class OnUpdatePriority(val priority: Boolean, val id: Int) : SubjectsEvent
    data class OnUpdateSubjectName(val name: String) : SubjectsEvent
}