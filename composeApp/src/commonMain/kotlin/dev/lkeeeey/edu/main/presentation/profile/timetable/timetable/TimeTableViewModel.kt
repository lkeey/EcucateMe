package dev.lkeeeey.edu.main.presentation.profile.timetable.timetable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkeeeey.edu.auth.domain.AuthRepository
import dev.lkeeeey.edu.core.domain.onError
import dev.lkeeeey.edu.core.domain.onSuccess
import dev.lkeeeey.edu.main.domain.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TimeTableViewModel (
    private val profileRepository: ProfileRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TimeTableState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    init {
        viewModelScope.launch {
            profileRepository.refreshToken()
                .onSuccess {
                    authRepository.updateAccessToken(it.accessToken)

                    profileRepository
                        .getTimeTable()
                        .onSuccess {
                            for (model in it) {

                                val modified = state.value.savedSubjects.toMutableList()
                                modified[model.weekDay].add(model.name.name)

                                _state.update {
                                    it.copy(
                                        savedSubjects = modified
                                    )
                                }

                                println("subjects - ${state.value.savedSubjects}")
                            }
                        }
                        .onError {
                            println("error - $it")
                        }
                }
                .onError {
                    println("error - $it")
                }
        }
    }

    fun onEvent(event : TimeTableEvent) {
        when (event) {
            TimeTableEvent.OnAddLesson -> {
                val modified = state.value.savedSubjects.toMutableList()
                modified[state.value.dayIndex].add("New")

                _state.update {
                    it.copy(
                        savedSubjects = modified
                    )
                }
            }
            TimeTableEvent.OnSaveDay -> {

            }
            is TimeTableEvent.OnSubjectUpdate -> {
                val modified = state.value.savedSubjects.toMutableList()
                modified[state.value.dayIndex][event.index] = event.subject

                _state.update {
                    it.copy(
                        savedSubjects = modified
                    )
                }
            }
            is TimeTableEvent.OnChangeDay -> {
                _state.update {
                    it.copy(
                        dayIndex = event.index
                    )
                }
            }
        }
    }
}
