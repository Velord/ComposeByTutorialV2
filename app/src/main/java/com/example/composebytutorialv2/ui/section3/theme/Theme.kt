package com.example.composebytutorialv2.ui.section3.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
    primary = RwPrimary,
    primaryVariant = RwPrimaryDark,
    onPrimary = Color.Gray,
    secondary = Color.LightGray,
    secondaryVariant = RwPrimaryDark,
    onSecondary = Color.Black,
    error = Red800
)

private val DarkThemeColors = darkColors(
    primary = RwPrimaryDark,
    primaryVariant = RwPrimary,
    onPrimary = Color.Gray,
    secondary = Color.Black,
    onSecondary = Color.White,
    error = Red800
)

@Composable
fun RedditTheme(content: @Composable () -> Unit) {
    val isDarkTheme = isSystemInDarkTheme() || RedditSettings.isDarkThemeEnabled
    val colors = if (isDarkTheme) DarkThemeColors else LightThemeColors

    MaterialTheme(colors = colors, content = content)
}

object RedditSettings {
    var isDarkThemeEnabled by mutableStateOf(false)

    fun switchTheme() {
        isDarkThemeEnabled = isDarkThemeEnabled.not()
    }
}