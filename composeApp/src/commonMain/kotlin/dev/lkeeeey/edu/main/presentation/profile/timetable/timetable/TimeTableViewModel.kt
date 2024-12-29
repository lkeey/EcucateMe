package dev.lkeeeey.edu.main.presentation.profile.timetable.timetable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russhwolf.settings.Settings
import dev.lkeeeey.edu.auth.data.keys.Keys
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

    private val settings = Settings()

    init {
        viewModelScope.launch {

            val access = settings.getString(
                key = Keys.ACCESS_TOKEN,
                defaultValue = ""
            )

            val refresh = settings.getString(
                key = Keys.REFRESH_TOKEN,
                defaultValue = ""
            )

            profileRepository.refreshToken()
                .onSuccess {
                    authRepository.updateAccessToken(it.accessToken)
                    val result = profileRepository.getTimeTable()

                    println("woooooooow - $result")
                }
                .onError {
                    println("error - $it")
                }
        }
    }

    fun onEvent(event : TimeTableEvent) {
        when (event) {
            TimeTableEvent.OnAddLesson -> {
                val modified = state.value.subjects + "New"
                _state.update {
                    it.copy(
                        subjects = modified
                    )
                }
            }
            TimeTableEvent.OnSaveDay -> {

            }
            is TimeTableEvent.OnSubjectUpdate -> {
                val modified = state.value.subjects.toMutableList()
                modified[event.index] = event.subject

                _state.update {
                    it.copy(
                        subjects = modified
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
