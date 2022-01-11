package com.example.composebytutorialv2.section1.chapter2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.navigation.Router
import com.example.composebytutorialv2.navigation.Screen
import com.example.composebytutorialv2.navigation.section1And2.BackButtonHandler

@Preview
@Composable
fun TextFieldScreen() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        MyTextField()
    }

    BackButtonHandler {
        Router.navigateTo(Screen.Section1.Navigation)
    }
}

@Composable
fun MyTextField() {
    val textValue = remember { mutableStateOf("") }
    val primaryColor = colorResource(id = R.color.purple_200)

    OutlinedTextField(
        value = textValue.value,
        onValueChange = { textValue.value = it },
        label = { Text(text = stringResource(id = R.string.email)) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = primaryColor,
            focusedLabelColor = primaryColor,
            cursorColor = primaryColor
        ),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
    )
}