package com.example.composebytutorialv2.ui.router

import androidx.compose.animation.Crossfade
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebytutorialv2.ui.chapter2.*

sealed class Screen {
    object Navigation : Screen()
    object Text : Screen()
    object TextField : Screen()
    object Buttons : Screen()
    object ProgressIndicator : Screen()
    object AlertDialog : Screen()
}

object JetFundamentalsRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.Navigation)

    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }
}

@Preview
@Composable
fun JetFundamentalApp() {
    Surface(color = MaterialTheme.colors.background) {
        Crossfade(targetState = JetFundamentalsRouter.currentScreen) {
            when(it.value) {
                is Screen.Navigation -> NavigationScreen()
                is Screen.Text -> TextScreen()
                is Screen.TextField -> TextFieldScreen()
                is Screen.Buttons -> ExploreButtonsScreen()
                is Screen.ProgressIndicator -> ProgressIndicatorScreen()
                is Screen.AlertDialog -> MyAlertDialogScreen()
            }
        }
    }
}