package dev.lkeeeey.edu.auth.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkeeeey.edu.auth.presentation.login.viewmodel.LoginViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoginView(
        state = state,
        onAction = { action ->
            viewModel.onAction(action)
        }
    )
}
