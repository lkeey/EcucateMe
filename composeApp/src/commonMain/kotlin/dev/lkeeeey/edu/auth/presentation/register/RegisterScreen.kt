package dev.lkeeeey.edu.auth.presentation.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkeeeey.edu.app.Route
import dev.lkeeeey.edu.auth.presentation.register.viewmodel.RegisterAction
import dev.lkeeeey.edu.auth.presentation.register.viewmodel.RegisterViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegisterScreen (
    viewModel: RegisterViewModel = koinViewModel(),
    navController: NavController
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    RegisterView(
        state = state,
        onAction = { action ->
            when (action) {
                is RegisterAction.OnLogin -> {
                    navController.navigate(Route.Login)
                }
                else -> {
                    viewModel.onAction(action)
                }
            }
        }
    )

}
