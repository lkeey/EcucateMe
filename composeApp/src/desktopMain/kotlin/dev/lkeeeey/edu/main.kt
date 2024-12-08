package dev.lkeeeey.edu

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.lkeeeey.edu.app.App
import dev.lkeeeey.edu.datastore.createDataStore
import dev.lkeeeey.edu.datastore.dataStoreFileName
import dev.lkeeeey.edu.di.initKoin

fun main() = application {

    initKoin()

    val prefs = createDataStore {
        dataStoreFileName
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "EcucateMe",
    ) {
        App(
            prefs = prefs
        )
    }
}