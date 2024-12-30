package dev.lkeeeey.edu.library.presentation.teacher_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkeeeey.edu.library.presentation.teachers.viewmodel.AllTeachersEvent
import dev.lkeeeey.edu.library.presentation.teachers.viewmodel.AllTeachersViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TeacherDetailScreen (
    viewModel: AllTeachersViewModel = koinViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TeacherDetailView(
        state = state,
        onEvent = { event->
            viewModel.onEvent(event)
        }
    )

    LaunchedEffect(true) {
        viewModel.onEvent(AllTeachersEvent.OnLoadTeacherDescription)
    }
}
