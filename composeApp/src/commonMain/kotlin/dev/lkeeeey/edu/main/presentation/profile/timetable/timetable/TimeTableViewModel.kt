package dev.lkeeeey.edu.main.presentation.profile.timetable.timetable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class TimeTableViewModel : ViewModel() {
    private val _state = MutableStateFlow(TimeTableState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val settings = Settings()

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