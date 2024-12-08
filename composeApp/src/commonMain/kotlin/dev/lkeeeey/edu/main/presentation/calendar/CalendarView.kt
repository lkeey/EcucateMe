package dev.lkeeeey.edu.main.presentation.calendar

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import dev.lkeeeey.edu.main.presentation.calendar.components.MonthText
import dev.lkeeeey.edu.main.presentation.calendar.components.MonthViewCalendar
import dev.lkeeeey.edu.main.presentation.calendar.viewmodel.CalendarEvent
import dev.lkeeeey.edu.main.presentation.calendar.viewmodel.CalendarState

@Composable
fun CalendarView (
    state: CalendarState,
    onEvent: (CalendarEvent) -> Unit
) {

    val loadedDates = state.loadedDates
    val selectedDate = state.selectedDate
    val currentMonth = state.currentDate.month

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .animateContentSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(12.dp)
                )
                .background(
                    color = White,
                    shape = RoundedCornerShape(
                        size = 8.dp
                    )
                )
        ) {

            /*
                month & toggle
            */
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MonthText(
                    selectedMonth = currentMonth
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            MonthViewCalendar(
                loadedDates,
                selectedDate,
                onDayClick = {
                    onEvent(CalendarEvent.OnDayClick(it))
                }
            )

        }
    }
}
