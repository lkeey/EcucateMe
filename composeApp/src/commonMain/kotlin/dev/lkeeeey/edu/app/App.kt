package dev.lkeeeey.edu.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import dev.lkeeeey.edu.auth.presentation.login.LoginScreen
import dev.lkeeeey.edu.auth.presentation.splash.SplashScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
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
                    SplashScreen(
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
                    LoginScreen(
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