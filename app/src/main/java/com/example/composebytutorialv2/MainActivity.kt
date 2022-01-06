package com.example.composebytutorialv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import com.example.composebytutorialv2.ui.router.JetFundamentalScreen

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetFundamentalScreen()
        }
    }
}

