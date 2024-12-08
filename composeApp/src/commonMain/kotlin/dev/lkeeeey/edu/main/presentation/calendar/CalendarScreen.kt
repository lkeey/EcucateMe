package dev.lkeeeey.edu.main.presentation.calendar

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkeeeey.edu.main.presentation.calendar.viewmodel.CalendarEvent
import dev.lkeeeey.edu.main.presentation.calendar.viewmodel.CalendarViewModel
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CalendarScreen (
    viewModel: CalendarViewModel = koinViewModel(),
    navController: NavController
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    CalendarView(
        state = state,
        onEvent = { event->
            viewModel.onEvent(event)
        }
    )
}
