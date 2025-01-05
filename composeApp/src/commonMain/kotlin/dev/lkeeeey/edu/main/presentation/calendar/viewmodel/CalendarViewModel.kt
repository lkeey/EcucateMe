package dev.lkeeeey.edu.main.presentation.calendar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russhwolf.settings.Settings
import dev.lkeeeey.edu.auth.domain.AuthRepository
import dev.lkeeeey.edu.core.domain.onSuccess
import dev.lkeeeey.edu.main.domain.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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
        }
    }

    fun loadSubjectsPerDay() {
        viewModelScope.launch {
            profileRepository.refreshToken()
                .onSuccess {
                    authRepository.updateAccessToken(it.accessToken)

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
                        }
                }
        }
    }

}
