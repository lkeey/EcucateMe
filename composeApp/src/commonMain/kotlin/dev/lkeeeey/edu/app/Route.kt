package dev.lkeeeey.edu.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Auth: Route
//    auth routes
    @Serializable
    data object Splash: Route

    @Serializable
    data object Login: Route

    @Serializable
    data object Register: Route
//    end auth routes


    @Serializable
    data object Main: Route


    @Serializable
    data object Calendar: Route


    @Serializable
    data object ProfileRoutes: Route
// profile routes
    @Serializable
    data object Profile: Route

    @Serializable
    data object ProfileSubjects: Route

    @Serializable
    data object ProfileTimeTable: Route

    @Serializable
    data object MyTeachers: Route
// end profile routes


    @Serializable
    data object LibraryRoutes: Route
// library routes
    @Serializable
    data object LibraryPosts: Route

    @Serializable
    data object AllTeachers: Route

    @Serializable
    data object TeacherDescription: Route
// end library routes

}