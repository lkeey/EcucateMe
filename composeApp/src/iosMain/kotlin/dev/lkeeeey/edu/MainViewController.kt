package dev.lkeeeey.edu

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import dev.lkeeeey.edu.app.App
import dev.lkeeeey.edu.datastore.createDataStore
import dev.lkeeeey.edu.di.initKoin

fun MainViewController() = ComposeUIViewController (
    configure = {
        initKoin()
    }
) {
    App(
        prefs = remember {
            createDataStore()
        }
    )
}