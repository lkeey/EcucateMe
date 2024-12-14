package dev.lkeeeey.edu.main.presentation.profile.timetable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkeeeey.edu.main.presentation.profile.timetable.timetable.TimeTableViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TimeTableScreen (
    viewModel: TimeTableViewModel = koinViewModel(),
    navController: NavController
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    TimeTableView(
        state = state,
        onEvent = { event->
            viewModel.onEvent(event)
        }
    )
}