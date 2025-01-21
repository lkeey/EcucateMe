package dev.lkeeeey.edu.main.presentation.profile.timetable.viewmodel

import dev.lkeeeey.edu.main.domain.models.SubjectPresModel
import dev.lkeeeey.edu.main.domain.models.TimeTableModel

data class TimeTableState(
    val dayIndex: Int = 0,
    val subjectIds: List<SubjectPresModel> = emptyList(),
    val savedSubjects: List<TimeTableModel> = emptyList(),
)