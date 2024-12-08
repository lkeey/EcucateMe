package dev.lkeeeey.edu.main.presentation.calendar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.core.presentation.Theme
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

/**
 * View that represent one day in the calendar
 * @param date date that view should represent
 * @param weekDayLabel flag that indicates if name of week day should be visible above day value
 * @param modifier view modifier
 */
@Composable
fun DayView(
    date: LocalDate,
    onDayClick: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
) {

    val isCurrentDay = date == Clock.System.todayIn(TimeZone.currentSystemDefault())

    val dayValueModifier =

        // if current day
        if (isCurrentDay) {
            modifier.background(
                color = Theme.colors.primaryBackground.copy(alpha = 0.75f),
                shape = CircleShape
            )
        }

        else if (isSelected) {
            modifier.background(
                color = Theme.colors.primaryBackground.copy(alpha = 1f),
                shape = CircleShape
            )
        }

        // if it is typically day
        else {
            modifier.background(
                color = White,
                shape = CircleShape
            )
        }


    Column(
        modifier = Modifier
            .wrapContentSize(),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        // show weekDay (Mon, Tue)

        Text (
            modifier = Modifier
                .size(25.dp),
            text = date.dayOfWeek.name.take(1),
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 12.sp,
                fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Thin)),
                fontWeight = FontWeight(400),
                color = Color(0xFF222222),
                letterSpacing = 0.2.sp,
                textAlign = TextAlign.Center
            )
        )

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .clickable {
                    onDayClick(date)
                },
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Box (
                modifier = Modifier
                    .padding(1.dp),
                contentAlignment = Alignment.Center
            ) {
                Text (
                    modifier = dayValueModifier
                        .padding(4.dp)
                        .size(24.dp),
                    text = date.dayOfMonth.toString(),
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Thin)),
                        fontWeight = FontWeight(400),
                        color =
                        if (isCurrentDay) {
                            White
                        } else {
                            Black
                        },
                        letterSpacing = 0.2.sp,
                        textAlign = TextAlign.Center
                    ),
                )
            }
        }
    }
}
