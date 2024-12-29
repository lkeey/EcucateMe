package dev.lkeeeey.edu.main.presentation.profile.subjects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkeeeey.edu.main.presentation.profile.timetable.TimeTableView
import dev.lkeeeey.edu.main.presentation.profile.timetable.viewmodel.TimeTableViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SubjectsView (
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
