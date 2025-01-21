package dev.lkeeeey.edu.app

import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.ic_bottom_calendar
import ecucateme.composeapp.generated.resources.ic_bottom_frame
import ecucateme.composeapp.generated.resources.ic_bottom_profile
import org.jetbrains.compose.resources.DrawableResource

sealed class BottomBarScreens(
    val route: Route,
    var title: String,
    val icon: DrawableResource
) {
    data object Main : BottomBarScreens(
        route = Route.Main,
        title = "Main",
        icon = Res.drawable.ic_bottom_calendar,
    )

    data object Library : BottomBarScreens(
        route = Route.LibraryRoutes,
        title = "Library",
        icon = Res.drawable.ic_bottom_frame,
    )

    data object Profile : BottomBarScreens(
        route = Route.ProfileRoutes,
        title = "Profile",
        icon = Res.drawable.ic_bottom_profile,
    )
}
