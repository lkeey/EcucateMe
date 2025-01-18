package dev.lkeeeey.edu.main.presentation.calendar.viewmodel

import dev.lkeeeey.edu.core.domain.getWeek
import dev.lkeeeey.edu.main.domain.models.SubjectModel
import dev.lkeeeey.edu.main.domain.models.SubjectPresModel
import dev.lkeeeey.edu.main.domain.models.TimeTableModel
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import network.chaintech.kmp_date_time_picker.utils.MIN

data class CalendarState (
    val subjects: List<TimeTableModel> = emptyList(),

    val loadedSubjectsPres: List<SubjectPresModel> = emptyList(),

    val enteredContent: String = "",
    val enteredDeadline: LocalDate = LocalDate.MIN(),
    val enteredTime: Int = 0,
    val enteredSubject: SubjectPresModel = SubjectPresModel(-1, "", false),

    val loadedDates: List<LocalDate> =
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.getWeek(),

    val currentDate: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
    val selectedDate: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
)

