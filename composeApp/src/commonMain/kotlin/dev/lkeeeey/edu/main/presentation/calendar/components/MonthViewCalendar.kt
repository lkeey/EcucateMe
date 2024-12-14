package dev.lkeeeey.edu.main.presentation.calendar.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun  MonthViewCalendar(
    loadedDates: List<LocalDate>,
    selectedDate: LocalDate,
    onDayClick: (LocalDate) -> Unit,
) {

    if (loadedDates.isNotEmpty()) {

        FlowRow (
            modifier = Modifier
                .fillMaxWidth()
        ) {

            loadedDates.forEach { date ->

                Box(
                    Modifier
                        .fillMaxWidth(0.14f)
                        .padding(5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    DayView(
                        date,
                        isSelected = selectedDate == date,
                        onDayClick = {
                            onDayClick(date)
                        },
                    )
                }
            }

        }


    }
}
