package dev.lkeeeey.edu.main.presentation.profile.subjects.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkeeeey.edu.auth.domain.AuthRepository
import dev.lkeeeey.edu.core.domain.onSuccess
import dev.lkeeeey.edu.main.domain.ProfileRepository
import dev.lkeeeey.edu.main.domain.models.SubjectPresModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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

    fun onEvent(
        event: SubjectsEvent
    ) {
        when (event) {
            SubjectsEvent.OnCreateSubject -> {
                viewModelScope.launch {
                    profileRepository
                        .refreshToken()
                        .onSuccess {
                            authRepository.updateAccessToken(it.accessToken)

                            profileRepository.createSubject(
                                subject = SubjectPresModel(
                                    id = 0,
                                    name = state.value.subjectName,
                                    priority = false
                                )
                            ).onSuccess {
                                getSubjects()

                            }
                        }
                }
            }
            is SubjectsEvent.OnUpdateSubjectName -> {
                _state.update {
                    it.copy(
                        subjectName = event.name
                    )
                }
            }
            is SubjectsEvent.OnUpdatePriority -> {
                viewModelScope.launch {
                    profileRepository
                        .refreshToken()
                        .onSuccess {
                            authRepository.updateAccessToken(it.accessToken)

                            profileRepository.updateSubject(
                                subject = SubjectPresModel(
                                    id = event.subject.id,
                                    name = event.subject.name,
                                    priority = event.subject.priority
                                )
                            ).onSuccess {
                                getSubjects()
                            }
                        }
                }
            }
        }
    }

    fun getSubjects() {
//        TODO SHOW LOADING

        viewModelScope.launch {
            profileRepository
                .refreshToken()
                .onSuccess {
                    authRepository.updateAccessToken(it.accessToken)

                    profileRepository
                        .getSubjects()
                        .onSuccess { s ->
                            _state.update {
                                it.copy(
                                    subjects = s
                                )
                            }
                        }
                }
        }
    }
}