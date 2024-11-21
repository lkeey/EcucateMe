package dev.lkeeeey.edu.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import dev.lkeeeey.edu.auth.presentation.login.LoginScreen
import dev.lkeeeey.edu.auth.presentation.login.viewmodel.LoginViewModel
import dev.lkeeeey.edu.auth.presentation.register.RegisterScreen
import dev.lkeeeey.edu.auth.presentation.register.viewmodel.RegisterViewModel
import dev.lkeeeey.edu.auth.presentation.splash.SplashScreen
import dev.lkeeeey.edu.auth.presentation.splash.viewmodel.SplashViewModel
import dev.lkeeeey.edu.core.presentation.EduMeTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    EduMeTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.Auth
        ) {
            navigation<Route.Auth>(
                startDestination = Route.Splash
            ) {
                composable<Route.Splash>(
                    exitTransition = { slideOutHorizontally() },
                    popEnterTransition = { slideInHorizontally() }
                ) {
//                    Splash screen

                    val viewModel = koinViewModel<SplashViewModel>()

                    SplashScreen(
                        viewModel = viewModel,
                        navController = navController
                    )
                }

                composable<Route.Login>(
                    enterTransition = { slideInHorizontally { initialOffset ->
                        initialOffset
                    } },
                    exitTransition = { slideOutHorizontally { initialOffset ->
                        initialOffset
                    } }
                ) {
//                    Login Screen

                    val viewModel = koinViewModel<LoginViewModel>()

                    LoginScreen(
                        viewModel = viewModel,
                        navController = navController
                    )
                }

                composable<Route.Register>(
                    enterTransition = { slideInHorizontally { initialOffset ->
                        initialOffset
                    } },
                    exitTransition = { slideOutHorizontally { initialOffset ->
                        initialOffset
                    } }
                ) {
//                    Register Screen

                    val viewModel = koinViewModel<RegisterViewModel>()

                    RegisterScreen(
                        viewModel = viewModel,
                        navController = navController
                    )
                }
            }
            navigation<Route.Main>(
                startDestination = Route.Calendar
            ) {
                composable<Route.Calendar>(
                    exitTransition = { slideOutHorizontally() },
                    popEnterTransition = { slideInHorizontally() }
                ) {
//                    Calendar screen
                    Column {
                        Text("Calendar")
                        Button(onClick = {
                            navController.navigate(Route.Profile)
                        }) {
                            Text("To profile")
                        }
                    }
                }

                composable<Route.Profile>(
                    exitTransition = { slideOutHorizontally() },
                    popEnterTransition = { slideInHorizontally() }
                ) {
//                    Profile screen
                    Text("Profile")
                }
            }
        }
    }
}