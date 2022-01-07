package com.example.composebytutorialv2.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.MutableState
import com.example.composebytutorialv2.navigation.JetFundamentalScreen
import com.example.composebytutorialv2.navigation.Screen
import com.example.composebytutorialv2.navigation.Section2Router
import com.example.composebytutorialv2.ui.section1.main.MainViewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetFundamentalScreen(router = Section2Router.currentScreen as MutableState<Screen>)
        }
    }
}

