package dev.lkeeeey.edu.main.presentation.profile.timetable.timetable

data class TimeTableState (
    val dayIndex : Int = 0,
    val subjects: List<String> = listOf("Математика", "Русский язык")
)