package dev.lkeeeey.edu.auth.presentation.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkeeeey.edu.app.Route
import dev.lkeeeey.edu.auth.presentation.login.viewmodel.LoginAction
import dev.lkeeeey.edu.auth.presentation.register.viewmodel.RegisterAction
import dev.lkeeeey.edu.auth.presentation.register.viewmodel.RegisterEvent
import dev.lkeeeey.edu.auth.presentation.register.viewmodel.RegisterViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegisterScreen (
    viewModel: RegisterViewModel = koinViewModel(),
    navController: NavController
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    when (state.event) {
        RegisterEvent.Nothing -> { }
        RegisterEvent.OpenLogin -> {
            navController.navigate(Route.Login)

            viewModel.onAction(RegisterAction.ClearEvents)
        }
        RegisterEvent.OpenMain -> {
            navController.navigate(Route.Calendar)

            viewModel.onAction(RegisterAction.ClearEvents)
        }
    }

    RegisterView(
        state = state,
        onAction = { action ->
            viewModel.onAction(action)
        }
    )

}
