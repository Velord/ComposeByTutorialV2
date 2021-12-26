package com.example.composebytutorialv2.ui.chapter2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.ui.router.BackButtonHandler
import com.example.composebytutorialv2.ui.router.JetFundamentalsRouter
import com.example.composebytutorialv2.ui.router.Screen

@Preview
@Composable
fun TextScreen() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        MyText()
    }

    BackButtonHandler {
        JetFundamentalsRouter.navigateTo(Screen.Navigation)
    }
}

@Preview
@Composable
fun MyText() {
    Text(
        text = stringResource(id = R.string.jetpack_compose),
        fontStyle = FontStyle.Italic,
        color = colorResource(id = R.color.teal_700),
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold
    )
}