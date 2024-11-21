package dev.lkeeeey.edu

import androidx.compose.ui.window.ComposeUIViewController
import dev.lkeeeey.edu.app.App
import dev.lkeeeey.edu.di.initKoin

fun MainViewController() = ComposeUIViewController (
    configure = {
        initKoin()
    }
) {
    App()
}