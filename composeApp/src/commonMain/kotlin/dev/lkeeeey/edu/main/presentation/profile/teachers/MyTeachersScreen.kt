package dev.lkeeeey.edu.main.presentation.profile.teachers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkeeeey.edu.app.Route
import dev.lkeeeey.edu.main.presentation.profile.teachers.viewmodel.MyTeachersAction
import dev.lkeeeey.edu.main.presentation.profile.teachers.viewmodel.MyTeachersEvent
import dev.lkeeeey.edu.main.presentation.profile.teachers.viewmodel.MyTeachersViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MyTeachersScreen (
    viewModel: MyTeachersViewModel = koinViewModel(),
    navController: NavController
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    MyTeachersView(
        state = state,
        onEvent = { event->
            viewModel.onEvent(event)
        },
        onOpenScreen = {
            when (it) {
                MyTeachersAction.OnOpenBack -> {
                    navController.popBackStack()
                }
                MyTeachersAction.OnOpenTeacherDescription -> {
                    navController.navigate(Route.TeacherDescription)
                }
            }
        }
    )

    LaunchedEffect(true) {
        viewModel.onEvent(MyTeachersEvent.OnSearchSelectedTeachers)
    }
}