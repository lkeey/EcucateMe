package dev.lkeeeey.edu.core.presentation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class EduMeColors(
    val primaryBackground: Color = Color.Black,
    val secondaryBackground: Color = Color.Black,
    val secondaryBorder: Color = Color.Black,
    val errorColor: Color = Color.Black,
    val primaryAction: Color = Color.Black,
    val defaultText: Color = Color.Black,
    val editPlaceholder: Color = Color.Black,
    val blackProfile: Color = Color.Black,

    val primaryTextColor: Color = Color.Black,
    val hintTextColor: Color = Color.Black,
    val highlightTextColor: Color = Color.Black,
    val secondaryTextColor: Color = Color.Black,
    val thirdTextColor: Color = Color.Black,
    val tagColor: Color = Color.Black,
    val tagTextColor: Color = Color.Black
)

val lightPalette = EduMeColors(
    primaryBackground = Color(0x17D46E),
    secondaryBackground = Color(0x00000000),
    secondaryBorder = Color(0xFF6D7885),
    errorColor = Color(0xFFF54135),
    primaryAction = Color(0x17D46E),
    defaultText = Color(0xFF222222),
    editPlaceholder = Color(0xFF6D7885)
)

val darkPalette = EduMeColors(
    primaryBackground = Color.Black,
)

val LocalColorProvider = staticCompositionLocalOf<EduMeColors> { error("No default implementation") }

