package dev.lkeeeey.edu.library.presentation.teachers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkeeeey.edu.app.Route
import dev.lkeeeey.edu.library.presentation.teachers.components.TabRows
import dev.lkeeeey.edu.library.presentation.teachers.viewmodel.AllTeachersAction
import dev.lkeeeey.edu.library.presentation.teachers.viewmodel.AllTeachersEvent
import dev.lkeeeey.edu.library.presentation.teachers.viewmodel.AllTeachersViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AllTeachersScreen (
    viewModel: AllTeachersViewModel = koinViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TabRows(
        state = state,
        onEvent = { event->
            viewModel.onEvent(event)
        },
        onOpen = {
            when (it) {
                AllTeachersAction.OnOpenTeacherDescription -> {
                    navController.navigate(Route.TeacherDescription)
                }
            }
        }
    )

    LaunchedEffect(true) {
        viewModel.onEvent(AllTeachersEvent.OnSearchTeachers)
    }
}
