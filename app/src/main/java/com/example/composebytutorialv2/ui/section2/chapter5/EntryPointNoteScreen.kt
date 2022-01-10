package com.example.composebytutorialv2.ui.section2.chapter5

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.navigation.Screen
import com.example.composebytutorialv2.ui.main.MainViewModel
import com.example.composebytutorialv2.ui.section2.chapter5.appDrawer.AppDrawer
import com.example.composebytutorialv2.ui.section2.chapter7.NoteCollectionScreen
import com.example.composebytutorialv2.ui.section2.theme.JetNotesTheme
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun EntryPointNoteScreen(viewModel: MainViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    val openOrClose: (Boolean) -> Unit = {
        coroutineScope.launch {
            if (it) scaffoldState.drawerState.open()
            else scaffoldState.drawerState.close()
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.jet_notes),
                        color = MaterialTheme.colors.onPrimary
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { openOrClose(true) }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.List,
                            tint = MaterialTheme.colors.onPrimary,
                            contentDescription = stringResource(R.string.drawer_button)
                        )
                    }
                }
            )
        },
        drawerContent = {
            AppDrawer(
                currentScreen = Screen.Section2.EntryPointNote,
                closeDrawerAction = { openOrClose(false) }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.createNewNote()
                },
                contentColor = MaterialTheme.colors.background,
                content = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(R.string.add_note_button)
                    )
                }
            )
        },
        content = {
            NoteCollectionScreen(viewModel)
        }
    )
}

@Preview
@Composable
fun EntryPointNoteScreenPreview() {
    JetNotesTheme {

    }
}