package dev.lkeeeey.edu.core.domain

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus

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
