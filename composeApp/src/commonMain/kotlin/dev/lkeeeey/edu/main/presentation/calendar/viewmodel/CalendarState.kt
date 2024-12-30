package dev.lkeeeey.edu.main.presentation.calendar.viewmodel

import dev.lkeeeey.edu.main.domain.models.TimeTableModel
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

data class CalendarState (
    val subjects: List<TimeTableModel> = emptyList(),

    val loadedDates: List<LocalDate> =
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.getWeek(),

    val currentDate: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
    val selectedDate: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
)

fun LocalDate.getWeek(): List<LocalDate> {
    val startDate = this.getWeekStartDate()
    val week = List(7) { index -> startDate.plus(index.toLong(), DateTimeUnit.DAY) }

    return week
}

fun LocalDate.getWeekStartDate(weekStartDay: DayOfWeek = DayOfWeek.MONDAY): LocalDate {
    var date = this
    while (date.dayOfWeek != weekStartDay) {
        date = date.minus(1, DateTimeUnit.DAY)
    }
    return date
}
