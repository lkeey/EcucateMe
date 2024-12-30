package dev.lkeeeey.edu.main.presentation.calendar

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.lkeeeey.edu.main.presentation.calendar.components.ImageWithText
import dev.lkeeeey.edu.main.presentation.calendar.components.MonthText
import dev.lkeeeey.edu.main.presentation.calendar.components.MonthViewCalendar
import dev.lkeeeey.edu.main.presentation.calendar.viewmodel.CalendarEvent
import dev.lkeeeey.edu.main.presentation.calendar.viewmodel.CalendarState
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.ic_calendar_no_plans
import ecucateme.composeapp.generated.resources.profile
import org.jetbrains.compose.resources.painterResource

@Composable
fun CalendarView (
    state: CalendarState,
    onEvent: (CalendarEvent) -> Unit,
    onOpenProfile: () -> Unit
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

                Image(
                    modifier = Modifier
                        .clip(CircleShape)
                        .height(50.dp)
                        .width(50.dp)
                        .clickable {
                            onOpenProfile()
                        },
                    painter = painterResource(Res.drawable.profile),
                    contentDescription = "profile img",
                    contentScale = ContentScale.FillWidth
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

            Spacer(Modifier.height(32.dp))

            ImageWithText (
                drawable = Res.drawable.ic_calendar_no_plans,
                text = "Здесь пусто. Можно и отдохнуть"
            )

            Spacer(Modifier.height(32.dp))

        }
    }
}
