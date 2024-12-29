package dev.lkeeeey.edu.main.presentation.profile.timetable.timetable

data class TimeTableState(
    val dayIndex: Int = 0,
    val savedSubjects: MutableList<MutableList<String>> = Array(7) { mutableListOf<String>() }.toMutableList(),
)