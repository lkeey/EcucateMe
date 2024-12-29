package dev.lkeeeey.edu.main.presentation.profile.timetable.viewmodel

data class TimeTableState(
    val dayIndex: Int = 0,
    val subjectIds: List<Map<String, Int>> = emptyList(),
    val savedSubjects: List<List<String>> = listOf(emptyList(), emptyList(), emptyList(), emptyList(), emptyList(), emptyList(), emptyList()),
)