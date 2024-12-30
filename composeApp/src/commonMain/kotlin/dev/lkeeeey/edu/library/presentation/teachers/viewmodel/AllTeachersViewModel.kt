package dev.lkeeeey.edu.library.presentation.teachers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkeeeey.edu.main.presentation.profile.timetable.viewmodel.TimeTableState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class AllTeachersViewModel (

) : ViewModel() {

    private val _state = MutableStateFlow(AllTeachersState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

}
