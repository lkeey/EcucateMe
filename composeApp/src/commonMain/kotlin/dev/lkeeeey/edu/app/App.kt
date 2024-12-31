package dev.lkeeeey.edu.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import dev.lkeeeey.edu.auth.presentation.login.LoginScreen
import dev.lkeeeey.edu.auth.presentation.login.viewmodel.LoginViewModel
import dev.lkeeeey.edu.auth.presentation.register.RegisterScreen
import dev.lkeeeey.edu.auth.presentation.register.viewmodel.RegisterViewModel
import dev.lkeeeey.edu.auth.presentation.splash.SplashScreen
import dev.lkeeeey.edu.auth.presentation.splash.viewmodel.SplashViewModel
import dev.lkeeeey.edu.core.presentation.EduMeTheme
import dev.lkeeeey.edu.core.presentation.Theme
import dev.lkeeeey.edu.library.presentation.teacher_detail.TeacherDetailScreen
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
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin
import org.jetbrains.compose.resources.painterResource
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

                    composable<Route.TeacherDescription>(
                        exitTransition = { slideOutHorizontally() },
                        popEnterTransition = { slideInHorizontally() }
                    ) {
//                    All teachers screen
                        val viewModel = koinViewModel<AllTeachersViewModel>()

                        TeacherDetailScreen(
                            viewModel = viewModel,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NavHostMain() {
    EduMeTheme {
        val navController = rememberNavController()

        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentScreen = backStackEntry?.destination

        Scaffold(
            bottomBar = {
                // don't show when auth
                BottomNavigation(
                    modifier = Modifier
                        .wrapContentSize()
                        .clip(RoundedCornerShape(40.dp, 40.dp, 0.dp, 0.dp)),
                    elevation = 4.dp,
                    backgroundColor = White,
                ) {

                    val screens = listOf(
                        BottomBarScreens.Main,
                        BottomBarScreens.Library,
                        BottomBarScreens.Profile
                    )

                    screens.forEach {
                        //println("currentScreen ${currentScreen?.parent?.route} ${it.route.toString()}")

                        val isOpened = currentScreen?.parent?.route?.contains(it.route.toString()) == true

                        BottomNavigationItem(
                            selected = isOpened,
                            onClick = {
                                if (isOpened) {
                                    // When we click again on a bottom bar item and it was already selected
                                    // we want to pop the back stack until the initial destination of this bottom bar item
                                    navController.popBackStack(it.route, false)
                                    return@BottomNavigationItem
                                }

                                navController.navigate(it.route) {
                                    // Avoid multiple copies of the same destination when
                                    // reselecting the same item
                                    launchSingleTop = true

                                    // Restore state when reselecting a previously selected item
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(it.icon),
                                    tint = if (isOpened) Theme.colors.primaryBackground.copy(1f) else Black,
                                    contentDescription = it.title,
                                )
                            },
                            label = {
                                Text(
                                    text = it.title,
                                    style = TextStyle(
                                        fontSize = 8.sp,
                                        fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Thin)),
                                        fontWeight = FontWeight(500),
                                        color = if (isOpened) Theme.colors.primaryBackground.copy(1f) else Black,
                                        textAlign = TextAlign.Center,
                                        letterSpacing = 0.2.sp,
                                    )
                                )
                            }
                        )
                    }

                }
            },
            backgroundColor = Theme.colors.backgroundMain
        ) {
            // nav host here
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

                        composable<Route.TeacherDescription>(
                            exitTransition = { slideOutHorizontally() },
                            popEnterTransition = { slideInHorizontally() }
                        ) {
//                    All teachers screen
                            val viewModel = koinViewModel<AllTeachersViewModel>()

                            TeacherDetailScreen(
                                viewModel = viewModel,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}

