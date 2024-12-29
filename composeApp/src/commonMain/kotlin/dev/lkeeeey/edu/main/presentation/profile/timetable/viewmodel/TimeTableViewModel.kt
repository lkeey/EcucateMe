package dev.lkeeeey.edu.main.presentation.profile.timetable.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkeeeey.edu.auth.domain.AuthRepository
import dev.lkeeeey.edu.core.domain.onError
import dev.lkeeeey.edu.core.domain.onSuccess
import dev.lkeeeey.edu.main.domain.ProfileRepository
import dev.lkeeeey.edu.main.domain.models.TimeTableModel
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

    fun onEvent(event : TimeTableEvent) {
        when (event) {
            TimeTableEvent.OnAddLesson -> {
                val m1 = state.value.savedSubjects.toMutableList()
                val m2 = m1[state.value.dayIndex].toMutableList()
                m2.add("New")
                m1[state.value.dayIndex] = m2

                _state.update {
                    it.copy(
                        savedSubjects = m1
                    )
                }
            }
            TimeTableEvent.OnSaveDay -> {
                updateSchedule()
            }
            is TimeTableEvent.OnSubjectUpdate -> {
                val m1 = state.value.savedSubjects.toMutableList()
                val m2 = m1[state.value.dayIndex].toMutableList()
                m2[event.index] = event.subject
                m1[state.value.dayIndex] = m2

                _state.update {
                    it.copy(
                        savedSubjects = m1
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

    fun getSavedSubjects() {
        viewModelScope.launch {
            profileRepository.refreshToken()
                .onSuccess {
                    authRepository.updateAccessToken(it.accessToken)

                    profileRepository
                        .getTimeTable()
                        .onSuccess {
                            for (model in it) {

                                val m1 = state.value.savedSubjects.toMutableList()
                                val m2 = m1[model.weekDay].toMutableList()
                                m2.add(model.name.name)
                                m1[model.weekDay] = m2

                                // TODO SAVE SUBJECT IDS to add when save
//                                if (state.value.subjectIds.contains(mapOf(Pair(model.name, model.id)))) {
//
//                                }

                                _state.update {
                                    it.copy(
                                        savedSubjects = m1
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


    fun updateSchedule() {
        val scheduleList = mutableListOf<TimeTableModel>()
        for (model in state.value.savedSubjects) {
//            TODO save schedule
//            scheduleList.add(
//                TimeTableModel(
//                    id =
//                )
//            )
        }
    }
}
