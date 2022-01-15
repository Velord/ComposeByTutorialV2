package com.example.composebytutorialv2.ui.screen.section1.chapter2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.composebytutorialv2.navigation.section1And2.BackButtonHandler

@Preview
@Composable
fun ExploreButtonsScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        MyButton()
        MyRadioGroup()
        MyFloatingActionButton()
    }

    BackButtonHandler {
        Router.navigateTo(Screen.Section1.Navigation)
    }
}

@Preview
@Composable
fun MyButton() {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
        border = BorderStroke(
            width = 1.dp,
            color = colorResource(id = R.color.teal_200)
        )
    ) {
        Text(
            text = stringResource(id = R.string.example),
            color = Color.White
        )
    }
}

@Preview
@Composable
fun MyRadioGroup() {
 val radioButtons = listOf(0, 1, 2)
    val selected = remember {
        mutableStateOf(radioButtons.first())
    }

    Box(Modifier.padding(top = 30.dp)) {
        Column(Modifier.padding(top = 12.dp)) {
            radioButtons.forEach {
                val isSelected = it == selected.value
                val colors = RadioButtonDefaults.colors(
                    selectedColor = colorResource(id = R.color.teal_200),
                    unselectedColor = colorResource(id = R.color.teal_700),
                    disabledColor = Color.LightGray
                )

                RadioButton(colors = colors, selected = isSelected, onClick = {
                    selected.value = it
                })
            }
        }
    }
}

@Preview
@Composable
fun MyFloatingActionButton() {
    FloatingActionButton(
        modifier = Modifier.padding(top = 30.dp),
        onClick = { /*TODO*/ },
        backgroundColor = colorResource(id = R.color.teal_200),
        contentColor = Color.White,
        content = {
            Icon(Icons.Filled.Favorite, contentDescription = "FAB")
        }
    )
}