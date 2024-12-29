package dev.lkeeeey.edu.main.presentation.profile.subjects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkeeeey.edu.main.presentation.profile.subjects.viewmodel.SubjectsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SubjectsScreen (
    viewModel: SubjectsViewModel = koinViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SubjectsView(
        state = state,
        onEvent = { event->
            viewModel.onEvent(event)
        }
    )

    LaunchedEffect(true) {
        viewModel.getSubjects()
    }
}
