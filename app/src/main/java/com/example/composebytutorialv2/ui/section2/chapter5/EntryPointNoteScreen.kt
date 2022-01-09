package com.example.composebytutorialv2.ui.section2.chapter5

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebytutorialv2.data.section2.model.NoteModel
import com.example.composebytutorialv2.navigation.Screen
import com.example.composebytutorialv2.ui.section2.chapter5.appDrawer.AppDrawer
import com.example.composebytutorialv2.ui.section2.theme.JetNotesTheme
import kotlinx.coroutines.launch

@Preview
@Composable
fun EntryPointNoteScreen() {
    JetNotesTheme {
        val coroutineScope = rememberCoroutineScope()
        val scaffoldState = rememberScaffoldState()

        Scaffold(
            scaffoldState = scaffoldState,
            drawerContent = {
                AppDrawer(
                    currentScreen = Screen.Section2.MainNote,
                    closeDrawerAction = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                )
            },
            content = {
                NoteListItemView(NoteModel())
            }
        )
    }
}