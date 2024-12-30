package dev.lkeeeey.edu.main.presentation.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkeeeey.edu.app.Route
import dev.lkeeeey.edu.main.presentation.calendar.viewmodel.CalendarViewModel
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
        },
        onOpenProfile = {
            navController.navigate(Route.Profile)
        }
    )

    LaunchedEffect(true) {
        viewModel.loadSubjectsPerDay()
    }
}
