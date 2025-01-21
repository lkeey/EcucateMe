package dev.lkeeeey.edu.main.presentation.profile.timetable.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkeeeey.edu.auth.domain.AuthRepository
import dev.lkeeeey.edu.core.domain.onError
import dev.lkeeeey.edu.core.domain.onSuccess
import dev.lkeeeey.edu.main.domain.ProfileRepository
import dev.lkeeeey.edu.main.domain.models.SubjectModel
import dev.lkeeeey.edu.main.domain.models.SubjectSchedule
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
                m1.add(
                    TimeTableModel(
                        id = -1,
                        weekDay = state.value.dayIndex,
                        start = "",
                        end = "",
                        name = SubjectModel(
                            name =  "New",
                            priority = false
                        )

                    )
                )

                _state.update {
                    it.copy(
                        savedSubjects = m1
                    )
                }
            }
            TimeTableEvent.OnSaveDay -> {
//                ALL UPDATED AFTER CHOOSING
//                updateSchedule()
            }
            is TimeTableEvent.OnSubjectUpdate -> {
                viewModelScope.launch {
                    profileRepository
                        .refreshToken()
                        .onSuccess {
                            authRepository.updateAccessToken(it.accessToken)

                            if (event.deletedSubject != -1) {
                                profileRepository
                                    .deleteSubjectFromSchedule(event.deletedSubject)
                                    .onSuccess {
                                        addSubject(
                                            subjectNum = event.subjectNum,
                                            subjectId = event.subjectNew.id
                                        )
                                    }
                            } else {
                                addSubject(
                                    subjectNum = event.subjectNum,
                                    subjectId = event.subjectNew.id
                                )
                            }

                        }
                }
            }
            is TimeTableEvent.OnChangeDay -> {
                _state.update {
                    it.copy(
                        dayIndex = event.index
                    )
                }

                getSavedSubjects()
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
                        .onSuccess { sub->
//                            for (model in it) {
//
//                                val m1 = state.value.savedSubjects.toMutableList()
//                                val m2 = m1[model.weekDay].toMutableList()
//                                m2.add(model.name.name)
//                                m1[model.weekDay] = m2
//
//                                _state.update {
//                                    it.copy(
//                                        savedSubjects = m1
//                                    )
//                                }
//
//                                println("subjects - ${state.value.savedSubjects}")
//                            }

                            // get schedule on current day
                            _state.update {
                                it.copy(
                                    savedSubjects = sub.filter { s-> s.weekDay == state.value.dayIndex }.sortedBy { it.start }
                                )
                            }


                            // get saved subjects
                            profileRepository
                                .getSubjects()
                                .onSuccess { s ->
                                    _state.update {
                                        it.copy(
                                            subjectIds = s
                                        )
                                    }
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

    private fun addSubject(
        subjectNum: Int,
        subjectId: Int,
    ) {
        var start = ""
        var end = ""

        when (subjectNum) {
            0 -> {
                start = "08:15:00"
                end = "08:55:00"
            }
            1 -> {
                start = "09:05:00"
                end = "09:45:00"
            }
            2 -> {
                start = "10:05:00"
                end = "10:45:00"
            }
            3 -> {
                start = "11:00:00"
                end = "11:40:00"
            }
            4 -> {
                start = "12:00:00"
                end = "12:40:00"
            }
        }
        viewModelScope.launch {
            profileRepository
                .addSubjectToSchedule(
                    subject = SubjectSchedule(
                        id = subjectId,
                        weekday = state.value.dayIndex,
                        start = start,
                        end = end
                    )
                )
                .onSuccess {
                    getSavedSubjects()
                }
        }
    }



}
