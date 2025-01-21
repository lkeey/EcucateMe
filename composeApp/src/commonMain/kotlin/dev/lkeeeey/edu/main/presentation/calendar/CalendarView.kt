package dev.lkeeeey.edu.main.presentation.calendar

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.core.presentation.Theme
import dev.lkeeeey.edu.main.presentation.calendar.components.BottomSheet
import dev.lkeeeey.edu.main.presentation.calendar.components.ImageWithText
import dev.lkeeeey.edu.main.presentation.calendar.components.MonthText
import dev.lkeeeey.edu.main.presentation.calendar.components.MonthViewCalendar
import dev.lkeeeey.edu.main.presentation.calendar.components.ScheduleSubject
import dev.lkeeeey.edu.main.presentation.calendar.components.ScheduledTask
import dev.lkeeeey.edu.main.presentation.calendar.viewmodel.CalendarAction
import dev.lkeeeey.edu.main.presentation.calendar.viewmodel.CalendarEvent
import dev.lkeeeey.edu.main.presentation.calendar.viewmodel.CalendarState
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin
import ecucateme.composeapp.generated.resources.ic_calendar_no_plans
import ecucateme.composeapp.generated.resources.profile
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarView (
    state: CalendarState,
    onEvent: (CalendarEvent) -> Unit,
    onOpen: (CalendarAction) -> Unit
) {

    val loadedDates = state.loadedDates
    val selectedDate = state.selectedDate
    val currentMonth = state.currentDate.month
    val scroll = rememberScrollState()

    val scope = rememberCoroutineScope()
    var isBottomSheetVisible by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .blur(if (state.isLoading) 4.dp else 0.dp),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        isBottomSheetVisible = true
                        sheetState.expand()
                    }
                },
                backgroundColor = Theme.colors.primaryBackground.copy(1f)
            ) {
                Icon(
                    tint = White,
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "add"
                )
            }
        },
        backgroundColor = Theme.colors.backgroundMain,
    ) { innerPadding->
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .animateContentSize()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
                .systemBarsPadding()
                .padding(horizontal = 16.dp)

        ) {

            if (state.error.isNotEmpty()) {
                Text(
                    text = state.error,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(Res.font.Thin)),
                        fontWeight = FontWeight(400),
                        color = Theme.colors.errorColor,
                        textAlign = TextAlign.Center
                    )
                )
            }

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
                                onOpen(CalendarAction.OnOpenProfile)
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
            }

            Column (
                modifier = Modifier
                    .verticalScroll(scroll)
            ) {
                Spacer(Modifier.height(32.dp))

                if (state.subjects.isEmpty()) {
                    ImageWithText (
                        drawable = Res.drawable.ic_calendar_no_plans,
                        text = "Здесь пусто. Можно и отдохнуть"
                    )
                } else {
                    state.subjects.forEach {
                        ScheduleSubject(subject = it)
                    }
                }

                state.distributionTasks.forEach {
                    ScheduledTask(distribution = it)
                }

                Spacer(Modifier.height(32.dp))
            }
        }
    }

    BottomSheet(
        state = state,
        isBottomSheetVisible = isBottomSheetVisible,
        sheetState = sheetState,
        onEvent = onEvent,
        onDismiss = {
            scope.launch { sheetState.hide() }
                .invokeOnCompletion { isBottomSheetVisible = false }
        }
    )
}
