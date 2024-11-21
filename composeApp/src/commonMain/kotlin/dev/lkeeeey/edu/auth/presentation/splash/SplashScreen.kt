package dev.lkeeeey.edu.auth.presentation.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkeeeey.edu.app.Route
import dev.lkeeeey.edu.auth.presentation.splash.viewmodel.SplashViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SplashScreen (
    viewModel: SplashViewModel = koinViewModel(),
    navController: NavController
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    when (state.isLoading) {
        true -> {
            SplashView(
                state = state
            )
        }
        false -> {
            // TODO add branch to navigate to main
            navController.navigate(Route.Login)
        }
    }


}
