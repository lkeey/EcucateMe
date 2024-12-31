package dev.lkeeeey.edu.app

import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.ic_subject
import ecucateme.composeapp.generated.resources.ic_tg
import ecucateme.composeapp.generated.resources.left_arrow
import org.jetbrains.compose.resources.DrawableResource

sealed class BottomBarScreens(
    val route: Route,
    var title: String,
    val icon: DrawableResource
) {
    data object Main : BottomBarScreens(
        route = Route.Main,
        title = "Main",
        icon = Res.drawable.ic_tg,
    )

    data object Library : BottomBarScreens(
        route = Route.LibraryRoutes,
        title = "Library",
        icon = Res.drawable.ic_tg,
    )

    data object Profile : BottomBarScreens(
        route = Route.ProfileRoutes,
        title = "Profile",
        icon = Res.drawable.ic_tg,
    )
}
