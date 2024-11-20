package dev.lkeeeey.edu.auth.presentation.splash

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import dev.lkeeeey.edu.app.Route

@Composable
fun SplashScreen (
    navController: NavController
) {

    Button(onClick = {
        navController.navigate(Route.Main)
    }) {
        Text("To login")
    }

    SplashView(

    )
}
