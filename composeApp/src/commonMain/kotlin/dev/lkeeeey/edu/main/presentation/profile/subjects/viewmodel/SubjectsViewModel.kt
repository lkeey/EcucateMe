package dev.lkeeeey.edu.main.presentation.profile.subjects.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkeeeey.edu.auth.domain.AuthRepository
import dev.lkeeeey.edu.main.domain.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SubjectsViewModel (
    private val profileRepository: ProfileRepository,
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow(SubjectsState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    fun onEvent(event: SubjectsEvent) {
        when (event) {
            SubjectsEvent.OnCreateSubject -> {

            }
            is SubjectsEvent.OnUpdateSubjectName -> {
                _state.update {
                    it.copy(
                        subjectName = event.name
                    )
                }
            }
            is SubjectsEvent.OnUpdatePriority -> {

            }
        }
    }
}