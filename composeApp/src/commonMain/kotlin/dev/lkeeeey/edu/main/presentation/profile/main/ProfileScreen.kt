package dev.lkeeeey.edu.main.presentation.profile.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkeeeey.edu.app.Route
import dev.lkeeeey.edu.main.presentation.profile.main.viewmodel.ProfileAction
import dev.lkeeeey.edu.main.presentation.profile.main.viewmodel.ProfileViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileScreen (
    viewModel: ProfileViewModel = koinViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProfileView(
        state = state,
        onEvent = {
            viewModel.onEvent(event = it)
        },
        onOpenScreen = { action->
            when (action) {
                ProfileAction.OnOpenTimeTable -> {
                    navController.navigate(Route.ProfileTimeTable)
                }

                ProfileAction.OnOpenSubjects -> {
                    navController.navigate(Route.ProfileSubjects)
                }

                ProfileAction.OnOpenLogin -> {
                    navController.navigate(Route.Auth)
                }

                ProfileAction.OnOpenTeachers -> {
                    navController.navigate(Route.MyTeachers)
                }
            }
        }
    )
}
