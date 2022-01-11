package com.example.composebytutorialv2.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.example.composebytutorialv2.navigation.Router
import com.example.composebytutorialv2.navigation.RouterScreen
import com.example.composebytutorialv2.ui.screen.section2.NotesViewModel
import com.example.composebytutorialv2.ui.screen.section3.RedditApp
import com.example.composebytutorialv2.ui.theme.section2.JetNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val noteViewModel by viewModels<NotesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Section3Screen()
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun Section2Screen(viewModel: NotesViewModel) {
    JetNotesTheme {
        Router.initSection2()
        RouterScreen(router = Router.currentScreen, viewModel = viewModel)
    }
}

@Composable
private fun Section3Screen() {
    RedditApp()
}

