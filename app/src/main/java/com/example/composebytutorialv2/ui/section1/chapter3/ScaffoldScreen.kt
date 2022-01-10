package com.example.composebytutorialv2.ui.section1.chapter3

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.navigation.BackButtonHandler
import com.example.composebytutorialv2.navigation.Screen
import com.example.composebytutorialv2.navigation.Section1Router
import com.example.composebytutorialv2.section1.chapter3.ColumnScreen
import com.example.composebytutorialv2.section1.chapter3.RowScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Preview
@Composable
fun ScaffoldScreen() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        contentColor = colorResource(id = R.color.teal_200),
        content = { RowScreen() },
        topBar = { MyTopAppBar(scaffoldState = scaffoldState, scope = scope) },
        bottomBar = { MyBottomAppBar() },
        drawerContent = { ColumnScreen() }
    )

    BackButtonHandler {
        Section1Router.navigateTo(Screen.Section1.Navigation)
    }
}

@Composable
fun MyTopAppBar(scaffoldState: ScaffoldState, scope: CoroutineScope) {
    val drawerState = scaffoldState.drawerState

    TopAppBar(
        navigationIcon = {
            IconButton(
                content = {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        tint = Color.Magenta,
                        contentDescription = stringResource(id = R.string.menu)
                    )
                },
                onClick = {
                    scope.launch {
                        if (drawerState.isClosed) drawerState.open()
                        else drawerState.close()
                    }
                }
            )
        },
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = Color.Red
            )
        },
        backgroundColor = colorResource(id = R.color.purple_200)
    )
}

@Composable
fun MyBottomAppBar() {
    BottomAppBar(
        content = {},
        backgroundColor = colorResource(id = R.color.teal_700)
    )
}