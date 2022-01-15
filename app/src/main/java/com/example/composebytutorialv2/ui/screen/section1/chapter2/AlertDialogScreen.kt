package com.example.composebytutorialv2.ui.screen.section1.chapter2

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.navigation.Router
import com.example.composebytutorialv2.navigation.Screen

@Preview
@Composable
fun MyAlertDialogScreen() {
    val shouldShowDialog = remember {
        mutableStateOf(true)
    }

    if (shouldShowDialog.value) {
        AlertDialog(
            onDismissRequest = {
                shouldShowDialog.value = false
                Router.navigateTo(Screen.Section1.Navigation)
            },
            title = {
                Text(text = stringResource(id = R.string.alert_title))
            },
            text = {
                Text(text = stringResource(id = R.string.alert_message))
            },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.teal_200)),
                    onClick = {
                        shouldShowDialog.value = false
                        Router.navigateTo(Screen.Section1.Navigation)
                    }
                ) {
                    Text(text = stringResource(id = R.string.confirm), color = Color.White)
                }
            }
        )
    }
}