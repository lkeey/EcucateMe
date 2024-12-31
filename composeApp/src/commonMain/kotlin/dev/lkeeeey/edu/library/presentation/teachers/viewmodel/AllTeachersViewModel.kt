package dev.lkeeeey.edu.library.presentation.teachers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russhwolf.settings.Settings
import dev.lkeeeey.edu.auth.data.keys.Keys
import dev.lkeeeey.edu.auth.domain.AuthRepository
import dev.lkeeeey.edu.core.domain.onSuccess
import dev.lkeeeey.edu.library.domain.LibraryRepository
import dev.lkeeeey.edu.main.domain.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AllTeachersViewModel (
    private val profileRepository: ProfileRepository,
    private val libraryRepository: LibraryRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AllTeachersState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val settings = Settings()

    fun onEvent(
        event: AllTeachersEvent
    ) {
        when (event) {
            is AllTeachersEvent.OnSubjectUpdate -> {
                _state.update {
                    it.copy(
                        subject = event.subject
                    )
                }

                searchTeachers()
            }

            AllTeachersEvent.OnSearchTeachers -> {
                searchTeachers()
            }

            is AllTeachersEvent.OnOpenTeacherDescription -> {
                settings.putString(
                    key = Keys.SELECTED_TEACHER,
                    value = event.username
                )
            }

            AllTeachersEvent.OnSelectTeacher -> {
                // toDO
            }

            AllTeachersEvent.OnLoadTeacherDescription -> {
                _state.update {
                    it.copy(
                        selectedUsername = settings.getString(Keys.SELECTED_TEACHER, "")
                    )
                }

                viewModelScope.launch {

                    profileRepository.refreshToken()
                        .onSuccess {
                            authRepository.updateAccessToken(it.accessToken)

                            libraryRepository.getTeacherDescription(
                                username = state.value.selectedUsername
                            )
                            .onSuccess { teacher ->
                                _state.update {
                                    it.copy(
                                        selectedTeacherModel = teacher
                                    )
                                }
                            }
                        }
                }

            }
        }
    }


    private fun searchTeachers () {
        viewModelScope.launch {

            profileRepository.refreshToken()
                .onSuccess {
                    authRepository.updateAccessToken(it.accessToken)

                    libraryRepository
                        .getAllTeachers(
                            query = state.value.query,
                            subject = state.value.subject
                        )
                        .onSuccess { t ->
                            _state.update {
                                it.copy(
                                    teachers = t
                                )
                            }
                        }
                }

        }
    }
}
