package dev.lkeeeey.edu.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Auth: Route

    @Serializable
    data object Splash: Route

    @Serializable
    data object Login: Route

    @Serializable
    data object Register: Route

    @Serializable
    data object Main: Route

    @Serializable
    data object Calendar: Route

    @Serializable
    data object ProfileRoutes: Route

    @Serializable
    data object Profile: Route

    @Serializable
    data object ProfileSubjects: Route

    @Serializable
    data object ProfileTimeTable: Route
}