package dev.lkeeeey.edu.library.presentation.tasks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FullTasksScreen (
    viewModel: FullTaskViewModel = koinViewModel<FullTaskViewModel>(),
    navController: NavController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    FullTasksView(
        state = state,
        onEvent = {
            viewModel.onEvent(it)
        },
        onOpenBack = {
            navController.popBackStack()
        }
    )

    LaunchedEffect(true) {
        viewModel.onEvent(FullTaskEvent.OnLoadFullModel)
    }

}