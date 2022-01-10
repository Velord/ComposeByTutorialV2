package com.example.composebytutorialv2.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.example.composebytutorialv2.navigation.JetNoteAppRouterScreen
import com.example.composebytutorialv2.navigation.JetNoteRouter
import com.example.composebytutorialv2.ui.section2.theme.JetNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            JetNotesTheme {
//                EntryPointNoteScreen(viewModel = viewModel)
//            }
            MainActivityScreen(viewModel = viewModel)
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun MainActivityScreen(viewModel: MainViewModel) {
    JetNotesTheme {
        JetNoteAppRouterScreen(router = JetNoteRouter.currentScreen, viewModel = viewModel)
    }
}

