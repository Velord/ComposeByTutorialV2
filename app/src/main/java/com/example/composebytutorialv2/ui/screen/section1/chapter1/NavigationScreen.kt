package com.example.composebytutorialv2.ui.screen.section1.chapter1

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.composebytutorialv2.navigation.Router
import com.example.composebytutorialv2.navigation.Screen

@Preview
@Composable
fun NavigationScreen() {
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
                .padding(vertical = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationButton(text = stringResource(id = R.string.text), screen = Screen.Section1.Text)
            NavigationButton(text = stringResource(id = R.string.text_field), screen = Screen.Section1.TextField)
            NavigationButton(text = stringResource(id = R.string.buttons), screen = Screen.Section1.Buttons)
            NavigationButton(text = stringResource(id = R.string.progress_indicator), screen = Screen.Section1.ProgressIndicator)
            NavigationButton(text = stringResource(id = R.string.alert_dialog), screen = Screen.Section1.AlertDialog)
            NavigationButton(text = stringResource(id = R.string.surface), screen = Screen.Section1.Surface)
            NavigationButton(text = stringResource(id = R.string.scaffold), screen = Screen.Section1.Scaffold)
            NavigationButton(text = stringResource(id = R.string.scrolling), screen = Screen.Section1.Scrolling)
            NavigationButton(text = stringResource(id = R.string.book_list), screen = Screen.Section1.BookList)
            NavigationButton(text = stringResource(id = R.string.grid), screen = Screen.Section1.Grid)
        }
    }
}

@Composable
fun NavigationButton(text: String, screen: Screen.Section1) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.teal_200)
        ),
        onClick = { Router.navigateTo(screen) },
        border = BorderStroke(2.dp, Color.Black)
    ) {
        Text(text = text, color = Color.Magenta)
    }
}