package dev.lkeeeey.edu.main.presentation.calendar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russhwolf.settings.Settings
import dev.lkeeeey.edu.auth.domain.AuthRepository
import dev.lkeeeey.edu.core.domain.onError
import dev.lkeeeey.edu.core.domain.onSuccess
import dev.lkeeeey.edu.main.domain.ProfileRepository
import dev.lkeeeey.edu.main.domain.models.DistributionModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.DateTimeFormatBuilder
import network.chaintech.kmp_date_time_picker.ui.date_range_picker.parseToLocalDate

class CalendarViewModel (
    private val profileRepository: ProfileRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CalendarState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val settings = Settings()

    fun onEvent(event : CalendarEvent) {
        when (event) {
            is CalendarEvent.OnDayClick -> {
                _state.update {
                    it.copy(
                        selectedDate = event.date
                    )
                }
                loadSubjectsPerDay()
            }
            is CalendarEvent.OnEnterContent -> {
                _state.update {
                    it.copy(
                        enteredContent = event.content
                    )
                }
            }
            is CalendarEvent.OnEnterDeadline -> {
                _state.update {
                    it.copy(
                        enteredDeadline = event.deadline
                    )
                }
            }
            is CalendarEvent.OnEnterExecutionTime -> {
                _state.update {
                    it.copy(
                        enteredTime = event.time
                    )
                }
            }
            is CalendarEvent.OnEnterSubject -> {
                _state.update {
                    it.copy(
                        enteredSubject = event.subject
                    )
                }
            }
            CalendarEvent.OnSave -> {
                // TODO save

                println("value - ${state.value}")
            }
        }
    }

    fun loadSubjectsPerDay() {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            profileRepository.refreshToken()
                .onSuccess { res ->
                    authRepository.updateAccessToken(res.accessToken)

                    profileRepository
                        .getTimeTable()
                        .onSuccess { sub->
                            _state.update {
                                it.copy(
                                    subjects = sub
                                        .filter {
                                            s-> s.weekDay == state.value.loadedDates.indexOf(state.value.selectedDate)
                                        }
                                        .sortedBy {
                                            s -> s.start
                                        }
                                )
                            }

                            profileRepository
                                .getDistributionTasks()
                                .onSuccess { tasks->
                                    val todayTasks = mutableListOf<DistributionModel>()

                                    for (t in tasks) {
                                        for (d in t.distribution) {
                                            if (d.start.split("T")[0].parseToLocalDate() == state.value.selectedDate) {
                                                todayTasks.add(d)
                                            }
                                        }
                                    }

                                    _state.update {
                                        it.copy(
                                            isLoading = false,
                                            distributionTasks = todayTasks
                                                .sortedBy {
                                                    d -> d.start
                                                }
                                        )
                                    }

                                }
                                .onError { e->
                                    _state.update {
                                        it.copy(
                                            isLoading = false,
                                            error = e.name
                                        )
                                    }
                                }
                        }
                        .onError { e->
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    error = e.name
                                )
                            }
                        }
                }
                .onError { e->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = e.name
                        )
                    }
                }
        }
    }

}
