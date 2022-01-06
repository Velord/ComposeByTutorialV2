package com.example.composebytutorialv2.ui.router

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebytutorialv2.ui.section1.chapter2.*
import com.example.composebytutorialv2.ui.section1.chapter3.ScaffoldScreen
import com.example.composebytutorialv2.ui.section1.chapter3.SurfaceScreen
import com.example.composebytutorialv2.ui.section1.chapter4.BookListScreen
import com.example.composebytutorialv2.ui.section1.chapter4.ScrollingScreen
import com.example.composebytutorialv2.ui.section1.chapter4.grid.GridScreen

sealed class Screen {
    object Navigation : Screen()
    object Text : Screen()
    object TextField : Screen()
    object Buttons : Screen()
    object ProgressIndicator : Screen()
    object AlertDialog : Screen()
    object Surface : Screen()
    object Scaffold : Screen()
    object Scrolling : Screen()
    object BookList : Screen()
    object Grid : Screen()
}

object JetFundamentalsRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.Navigation)

    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun JetFundamentalScreen() {
    Surface(color = MaterialTheme.colors.background) {
        Crossfade(targetState = JetFundamentalsRouter.currentScreen) {
            when(it.value) {
                is Screen.Navigation -> NavigationScreen()
                is Screen.Text -> TextScreen()
                is Screen.TextField -> TextFieldScreen()
                is Screen.Buttons -> ExploreButtonsScreen()
                is Screen.ProgressIndicator -> ProgressIndicatorScreen()
                is Screen.AlertDialog -> MyAlertDialogScreen()
                is Screen.Surface -> SurfaceScreen()
                is Screen.Scaffold -> ScaffoldScreen()
                is Screen.Scrolling -> ScrollingScreen()
                is Screen.BookList -> BookListScreen()
                is Screen.Grid -> GridScreen()
            }
        }
    }
}