package com.example.composebytutorialv2.ui.screen.section1.chapter2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.navigation.section1And2.BackButtonHandler
import com.example.composebytutorialv2.navigation.section1And2.Router
import com.example.composebytutorialv2.navigation.section1And2.Screen

@Preview
@Composable
fun ProgressIndicatorScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            color = colorResource(id = R.color.teal_200),
            strokeWidth = 5.dp
        )
        LinearProgressIndicator(modifier = Modifier.padding(top = 16.dp), progress = 0.6f)
    }

    BackButtonHandler {
        Router.navigateTo(Screen.Section1.Navigation)
    }
}