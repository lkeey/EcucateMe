package dev.lkeeeey.edu.main.presentation.calendar.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import dev.lkeeeey.edu.main.presentation.calendar.viewmodel.CalendarEvent
import dev.lkeeeey.edu.main.presentation.calendar.viewmodel.CalendarState
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun ExpandableCalendar(
    state: CalendarState,
//    onDayClick: (LocalDate) -> Unit,
    onEvent: (CalendarEvent) -> Unit
) {

//    val loadedDates = viewModel.visibleDates.collectAsState()
//    val selectedDate = viewModel.selectedDate.collectAsState()
//    val calendarExpanded = viewModel.calendarExpanded.collectAsState()
//    val currentMonth = viewModel.currentMonth.collectAsState()
//    var isShouldFlipping by remember {
//        mutableStateOf(true)
//    }


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
                .clickable(
                    onClick = {
//                        onEvent(Event.CancelSearching)
                    }
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
//                    selectedMonth = currentMonth.value
                )

                ToggleExpandCalendarButton(
                    isExpanded = true,
                    expand = {
//                        viewModel.onIntent(CalendarIntent.ExpandCalendar)
                    },
                    collapse = {
//                        viewModel.onIntent(CalendarIntent.CollapseCalendar)
                    },
                )
            }

//            Spacer(modifier = Modifier.height(16.dp))

            val currentMoment = Clock.System.now()
            val localDateTime = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())

            Text("localDateTime - $localDateTime")

        }
    }
}
