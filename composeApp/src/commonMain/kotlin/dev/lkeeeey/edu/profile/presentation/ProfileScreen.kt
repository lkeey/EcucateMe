package dev.lkeeeey.edu.profile.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkeeeey.edu.app.Route
import dev.lkeeeey.edu.profile.presentation.viewmodel.ProfileAction
import dev.lkeeeey.edu.profile.presentation.viewmodel.ProfileViewModel
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
            }
        }
    )
}
