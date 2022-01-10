package com.example.composebytutorialv2.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebytutorialv2.section1.chapter2.*
import com.example.composebytutorialv2.section1.chapter3.SurfaceScreen
import com.example.composebytutorialv2.section1.chapter4.BookListScreen
import com.example.composebytutorialv2.section1.chapter4.ScrollingScreen
import com.example.composebytutorialv2.ui.main.MainViewModel
import com.example.composebytutorialv2.ui.section1.chapter1.NavigationScreen
import com.example.composebytutorialv2.ui.section1.chapter3.ScaffoldScreen
import com.example.composebytutorialv2.ui.section1.chapter4.grid.GridScreen
import com.example.composebytutorialv2.ui.section2.chapter5.EntryPointNoteScreen
import com.example.composebytutorialv2.ui.section2.chapter7.NoteCollectionScreen
import com.example.composebytutorialv2.ui.section2.chapter8.SaveNoteScreen
import com.example.composebytutorialv2.ui.section2.chapter8.TrashScreen

sealed class Screen {
    sealed class Section1 : Screen() {
        object Navigation : Section1()
        object Text : Section1()
        object TextField : Section1()
        object Buttons : Section1()
        object ProgressIndicator : Section1()
        object AlertDialog : Section1()
        object Surface : Section1()
        object Scaffold : Section1()
        object Scrolling : Section1()
        object BookList : Section1()
        object Grid : Section1()
    }

    sealed class Section2 : Screen() {
        object EntryPointNote : Section2()
        object SaveNote : Section2()
        object Trash : Section2()
        object NoteList: Section2()
    }
}

object Section1Router {
    var currentScreen: MutableState<Screen.Section1> = mutableStateOf(Screen.Section1.Navigation)

    fun navigateTo(destination: Screen.Section1) {
        currentScreen.value = destination
    }
}

@Preview
@Composable
fun Section1RouterScreen(
    router: MutableState<Screen.Section1> = mutableStateOf(Screen.Section1.Navigation)
) {
    Surface(color = MaterialTheme.colors.background) {
        Crossfade(targetState = router) {
            when(it.value) {
                is Screen.Section1.Navigation -> NavigationScreen()
                is Screen.Section1.Text -> TextScreen()
                is Screen.Section1.TextField -> TextFieldScreen()
                is Screen.Section1.Buttons -> ExploreButtonsScreen()
                is Screen.Section1.ProgressIndicator -> ProgressIndicatorScreen()
                is Screen.Section1.AlertDialog -> MyAlertDialogScreen()
                is Screen.Section1.Surface -> SurfaceScreen()
                is Screen.Section1.Scaffold -> ScaffoldScreen()
                is Screen.Section1.Scrolling -> ScrollingScreen()
                is Screen.Section1.BookList -> BookListScreen()
                is Screen.Section1.Grid -> GridScreen()
            }
        }
    }
}

object JetNoteRouter {
    var currentScreen: MutableState<Screen.Section2> = mutableStateOf(Screen.Section2.EntryPointNote)

    fun navigateTo(destination: Screen.Section2) {
        currentScreen.value = destination
    }
}


@ExperimentalMaterialApi
@Composable
fun JetNoteAppRouterScreen(
    router: MutableState<Screen.Section2>,
    viewModel: MainViewModel
) {
    Surface(color = MaterialTheme.colors.background) {
        Crossfade(targetState = router) {
            when(it.value) {
                is Screen.Section2.EntryPointNote -> EntryPointNoteScreen(viewModel)
                is Screen.Section2.SaveNote -> SaveNoteScreen(viewModel)
                is Screen.Section2.Trash -> TrashScreen(viewModel)
                is Screen.Section2.NoteList -> NoteCollectionScreen(viewModel)
            }
        }
    }
}