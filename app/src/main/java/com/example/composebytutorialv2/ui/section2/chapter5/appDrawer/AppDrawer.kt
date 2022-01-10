package com.example.composebytutorialv2.ui.section2.chapter5.appDrawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.navigation.JetNoteRouter
import com.example.composebytutorialv2.navigation.Screen
import com.example.composebytutorialv2.ui.section2.theme.JetNotesTheme
import com.example.composebytutorialv2.ui.section2.theme.ThemeItem

@Composable
fun AppDrawer(
    currentScreen: Screen.Section2,
    closeDrawerAction: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        AppDrawerHeader()

        Divider(color = MaterialTheme.colors.onSurface.copy(0.2f))

        ScreenNavigationButton(
            icon = Icons.Filled.Home,
            label = stringResource(R.string.notes),
            isSelected = currentScreen == Screen.Section2.EntryPointNote,
            onClick = {
                JetNoteRouter.navigateTo(Screen.Section2.EntryPointNote)
                closeDrawerAction()
            }
        )

        ScreenNavigationButton(
            icon = Icons.Filled.Delete,
            label = stringResource(R.string.trash),
            isSelected = currentScreen == Screen.Section2.Trash,
            onClick = {
                JetNoteRouter.navigateTo(Screen.Section2.Trash)
                closeDrawerAction()
            }
        )

        ThemeItem()
    }
}

@Preview
@Composable
fun AppDrawerPreview() {
    JetNotesTheme {
        AppDrawer(Screen.Section2.EntryPointNote) {}
    }
}