package dev.lkeeeey.edu.core.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalViewConfiguration

@Composable
fun EduMeTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColorProvider provides if (isSystemInDarkTheme()) darkPalette else lightPalette,
        content = content
    )
}

object Theme {
    val colors: EduMeColors
        @Composable
        get() = LocalColorProvider.current
}
