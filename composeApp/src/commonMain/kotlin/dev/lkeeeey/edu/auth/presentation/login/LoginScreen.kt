package dev.lkeeeey.edu.auth.presentation.login

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkeeeey.edu.app.Route
import dev.lkeeeey.edu.auth.presentation.login.viewmodel.LoginAction
import dev.lkeeeey.edu.auth.presentation.login.viewmodel.LoginEvent
import dev.lkeeeey.edu.auth.presentation.login.viewmodel.LoginViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    navController: NavController,
    scaffoldState: ScaffoldState
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (state.event) {
        LoginEvent.OpenMain -> {
            navController.navigate(Route.Calendar)

            viewModel.onAction(LoginAction.ClearEvents)
        }
        LoginEvent.OpenSignUp -> {
            navController.navigate(Route.Register)

            viewModel.onAction(LoginAction.ClearEvents)
        }
        LoginEvent.Nothing -> {  }
    }

    LoginView(
        scaffoldState = scaffoldState,
        state = state,
        onAction = { action ->
            viewModel.onAction(action)
        }
    )
}
