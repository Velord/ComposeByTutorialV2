package com.example.composebytutorialv2.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.composebytutorialv2.ui.section2.chapter7.NoteCollectionScreen
import com.example.composebytutorialv2.ui.section2.theme.JetNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetNotesTheme {
                NoteCollectionScreen(viewModel = viewModel)
            }

            //JetFundamentalScreen(router = Section2Router.currentScreen as MutableState<Screen>)
        }
    }
}

