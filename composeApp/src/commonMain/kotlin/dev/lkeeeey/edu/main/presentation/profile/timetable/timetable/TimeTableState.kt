package dev.lkeeeey.edu.main.presentation.profile.timetable.timetable

data class TimeTableState(
    val dayIndex: Int = 0,
    val savedSubjects: List<List<String>> = listOf(emptyList(), emptyList(), emptyList(), emptyList(), emptyList(), emptyList(), emptyList()),
)