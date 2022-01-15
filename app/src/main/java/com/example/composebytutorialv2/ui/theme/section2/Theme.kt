package com.example.composebytutorialv2.ui.theme.section2

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
    primary = rwGreen,
    primaryVariant = rwGreenDark,
    secondary = rwGreen
)

private val DarkThemeColors = lightColors(
    primary = Color.Black,
    onPrimary = Color.LightGray,
    primaryVariant = Color(0xFF00F884),
    background = Color(0xff01001d),
    onBackground = Color.LightGray,
    surface = Color(0xff011d2f),
    onSurface = Color.LightGray
)

@Composable
fun JetNotesTheme(content: @Composable () -> Unit) {
    val isDarkThemeEnabled = isSystemInDarkTheme() || JetNotesThemeSettings.isDarkThemeEnabled
    val colors = if (isDarkThemeEnabled) DarkThemeColors else LightThemeColors

    MaterialTheme(colors = colors, content = content)
}

/**
 * Allows changing between light and a dark theme from the app's settings.
 */
object JetNotesThemeSettings {
    var isDarkThemeEnabled by mutableStateOf(false)
}