package dev.lkeeeey.edu.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
import dev.lkeeeey.edu.library.presentation.teachers.AllTeachersScreen
import dev.lkeeeey.edu.library.presentation.teachers.viewmodel.AllTeachersViewModel
import dev.lkeeeey.edu.main.presentation.calendar.CalendarScreen
import dev.lkeeeey.edu.main.presentation.calendar.viewmodel.CalendarViewModel
import dev.lkeeeey.edu.main.presentation.profile.subjects.SubjectsScreen
import dev.lkeeeey.edu.main.presentation.profile.subjects.viewmodel.SubjectsViewModel
import dev.lkeeeey.edu.main.presentation.profile.timetable.TimeTableScreen
import dev.lkeeeey.edu.main.presentation.profile.timetable.viewmodel.TimeTableViewModel
import dev.lkeeeey.edu.profile.presentation.ProfileScreen
import dev.lkeeeey.edu.profile.presentation.viewmodel.ProfileViewModel
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

                    val viewModel = koinViewModel<CalendarViewModel>()

                    CalendarScreen(
                        viewModel = viewModel,
                        navController = navController
                    )

                }

                navigation<Route.ProfileRoutes>(
                    startDestination = Route.Profile
                ) {
                    composable<Route.Profile>(
                        exitTransition = { slideOutHorizontally() },
                        popEnterTransition = { slideInHorizontally() }
                    ) {
//                    Profile screen
                        val viewModel = koinViewModel<ProfileViewModel>()

                        ProfileScreen(
                            viewModel = viewModel,
                            navController = navController
                        )
                    }

                    composable<Route.ProfileTimeTable>(
                        exitTransition = { slideOutHorizontally() },
                        popEnterTransition = { slideInHorizontally() }
                    ) {
//                    ProfileTimeTable screen
                        val viewModel = koinViewModel<TimeTableViewModel>()

                        TimeTableScreen(
                            viewModel = viewModel,
                            navController = navController
                        )
                    }

                    composable<Route.ProfileSubjects>(
                        exitTransition = { slideOutHorizontally() },
                        popEnterTransition = { slideInHorizontally() }
                    ) {
//                    Subjects screen
                        val viewModel = koinViewModel<SubjectsViewModel>()

                        SubjectsScreen(
                            viewModel = viewModel,
                            navController = navController
                        )
                    }
                }

                navigation<Route.LibraryRoutes>(
                    startDestination = Route.LibraryPosts
                ) {
                    composable<Route.LibraryPosts>(
                        exitTransition = { slideOutHorizontally() },
                        popEnterTransition = { slideInHorizontally() }
                    ) {
//                    Library Posts screen
//                        TODO
                        Text("library posts")
                    }

                    composable<Route.AllTeachers>(
                        exitTransition = { slideOutHorizontally() },
                        popEnterTransition = { slideInHorizontally() }
                    ) {
//                    All teachers screen
                        val viewModel = koinViewModel<AllTeachersViewModel>()

                        AllTeachersScreen(
                            viewModel = viewModel,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}