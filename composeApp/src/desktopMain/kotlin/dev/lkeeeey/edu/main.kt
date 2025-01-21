package dev.lkeeeey.edu

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.lkeeeey.edu.app.App
import dev.lkeeeey.edu.di.initKoin

fun main() = application {

    initKoin()

    Window(
        onCloseRequest = ::exitApplication,
        title = "EcucateMe",
    ) {
        App()
    }
}