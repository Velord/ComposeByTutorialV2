package com.example.composebytutorialv2.ui.chapter2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.ui.router.JetFundamentalsRouter
import com.example.composebytutorialv2.ui.router.Screen

@Preview
@Composable
fun NavigationScreen() {
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationButton(text = stringResource(id = R.string.text), screen = Screen.Text)
            NavigationButton(text = stringResource(id = R.string.text_field), screen = Screen.TextField)
            NavigationButton(text = stringResource(id = R.string.buttons), screen = Screen.Buttons)
            NavigationButton(text = stringResource(id = R.string.progress_indicator), screen = Screen.ProgressIndicator)
            NavigationButton(text = stringResource(id = R.string.alert_dialog), screen = Screen.AlertDialog)
        }
    }
}

@Composable
fun NavigationButton(text: String, screen: Screen) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.teal_200)
        ),
        onClick = { JetFundamentalsRouter.navigateTo(screen) },
    ) {
        Text(text = text, color = Color.White)
    }
}