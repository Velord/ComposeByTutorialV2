package com.example.composebytutorialv2.ui.screen.section2.chapter8

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.data.model.section2.ColorModel
import com.example.composebytutorialv2.data.model.section2.NEW_NOTE_ID
import com.example.composebytutorialv2.data.model.section2.NoteModel
import com.example.composebytutorialv2.navigation.Router
import com.example.composebytutorialv2.navigation.Screen
import com.example.composebytutorialv2.ui.component.section2.chapter6.ColorPickerView
import com.example.composebytutorialv2.ui.component.section2.chapter6.NoteColorView
import com.example.composebytutorialv2.ui.screen.section2.NotesViewModel
import kotlinx.coroutines.launch

//Crash: "The initial value must have an associated anchor"
//When BottomDrawer(Color Picker) has BottomDrawerValue.Open
//BottomDrawerValue.Expanded works fine
@ExperimentalMaterialApi
@Composable
fun SaveNoteScreen(viewModel: NotesViewModel) {
    val noteEntry by viewModel.noteEntry.collectAsState(NoteModel())
    val colors by viewModel.allColors.collectAsState(emptyList())

    val bottomDrawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    val moveToTrashDialogShownState = rememberSaveable {
        mutableStateOf(false)
    }

    BackHandler {
        if (moveToTrashDialogShownState.value)
            moveToTrashDialogShownState.value = false

        if (bottomDrawerState.isOpen) {
            coroutineScope.launch { bottomDrawerState.close() }
        } else {
            Router.navigateTo(Screen.Section2.EntryPointNote)
        }
    }

    val openColorPicker: () -> Unit = {
        coroutineScope.launch {
            bottomDrawerState.open()
        }
    }
    val selectColor: (ColorModel) -> Unit = {
        val newNote = noteEntry.copy(color = it)
        viewModel.noteEntryChange(newNote)

        coroutineScope.launch {
            bottomDrawerState.close()
        }
    }
    Scaffold(
        topBar = {
            SaveNoteTopAppBar(
                isEditingMode = noteEntry.id != NEW_NOTE_ID,
                onBackClick = {
                    Router.navigateTo(Screen.Section2.EntryPointNote)
                },
                onSaveNoteClick = {
                    viewModel.saveNote(noteEntry)
                },
                onOpenColorPickerClick = openColorPicker,
                onDeleteNoteClick = {
                    moveToTrashDialogShownState.value = true
                }
            )
        },
        content = {
            BottomDrawer(
                gesturesEnabled = true,
                drawerState = bottomDrawerState,
                drawerContent = {
                    ColorPickerView(
                        colors = colors,
                        onColorSelect = selectColor
                    )
                },
                content = {
                    SaveNoteContent(
                        note = noteEntry,
                        onNoteChange = viewModel::noteEntryChange,
                        onColorClick = openColorPicker
                    )
                },
            )

            DrawAlertDialog(noteEntry, viewModel, moveToTrashDialogShownState)
        }
    )
}

@Composable
private fun SaveNoteTopAppBar(
    isEditingMode: Boolean,
    onBackClick: () -> Unit,
    onSaveNoteClick: () -> Unit,
    onOpenColorPickerClick: () -> Unit,
    onDeleteNoteClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.save_note),
                color = MaterialTheme.colors.onPrimary
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    tint = MaterialTheme.colors.onPrimary,
                    contentDescription = stringResource(R.string.save_note_button)
                )
            }
        },
        actions = {
            IconButton(onClick = onSaveNoteClick) {
                Icon(
                    imageVector = Icons.Default.Check,
                    tint = MaterialTheme.colors.onPrimary,
                    contentDescription = stringResource(R.string.save_note)
                )
            }
            IconButton(onClick = onOpenColorPickerClick) {
                Icon(
                    imageVector = Icons.Default.PieChart,
                    tint = MaterialTheme.colors.onPrimary,
                    contentDescription = stringResource(R.string.open_color_picker_button)
                )
            }
            if (isEditingMode) {
                IconButton(onClick = onDeleteNoteClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        tint = MaterialTheme.colors.onPrimary,
                        contentDescription = stringResource(R.string.delete_note_button)
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun SaveNoteTopAppBarPreview() {
    SaveNoteTopAppBar(true, {}, {}, {}, {})
}

@Composable
private fun SaveNoteContent(
    note: NoteModel,
    onNoteChange: (NoteModel) -> Unit,
    onColorClick: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        ContentTextField(
            label = stringResource(R.string.title),
            text = note.title,
            onTextChange = {
                onNoteChange(note.copy(title = it))
            }
        )
        ContentTextField(
            modifier = Modifier
                .heightIn(max = 240.dp)
                .padding(top = 16.dp),
            label = stringResource(R.string.body),
            text = note.content,
            onTextChange = {
                onNoteChange(note.copy(content = it))
            }
        )

        val canBeCheckedOff = note.isCheckedOff != null
        NoteCheckOption(
            isChecked = canBeCheckedOff,
            onCheckedChange = {
                val isCheckedOff = if (it) false else null
                onNoteChange(note.copy(isCheckedOff = isCheckedOff))
            }
        )

        PickedColor(color = note.color, onColorClick = onColorClick)
    }
}

@Preview
@Composable
private fun SaveNoteContentPreview() {
    SaveNoteContent(
        note = NoteModel(title = "Title", content = "content"),
        onNoteChange = {},
        onColorClick = {}
    )
}

@Composable
private fun ContentTextField(
    modifier: Modifier = Modifier,
    label: String,
    text: String,
    onTextChange: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        label = { Text(label) },
        modifier = modifier.fillMaxWidth().padding(horizontal = 8.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface
        )
    )
}

@Preview
@Composable
private fun ContentTextFieldPreview() {
    ContentTextField(label = "Title", text = "", onTextChange = {})
}

@Composable
private fun NoteCheckOption(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(Modifier.padding(8.dp).padding(top = 16.dp)) {
        Text(
            text = stringResource(R.string.can_note_be_checked_off),
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            Modifier.padding(start = 8.dp)
        )
    }
}

@Preview
@Composable
private fun NoteCheckOptionPreview() {
    NoteCheckOption(isChecked = false) {}
}

@Composable
private fun PickedColor(
    color: ColorModel,
    onColorClick: () -> Unit
) {
    Row(Modifier.padding(8.dp).padding(top = 16.dp)) {
        Text(
            text = stringResource(R.string.picked_color),
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        NoteColorView(
            size = 40.dp,
            color = color.getGraphicColor(),
            borderSize = 1.dp,
            modifier = Modifier
                .padding(4.dp)
                .clickable { onColorClick() }
        )
    }
}

@Preview
@Composable
private fun PickedColorPreview() {
    PickedColor(ColorModel.DEFAULT) {}
}

@Composable
private fun DrawAlertDialog(
    noteEntry: NoteModel,
    viewModel: NotesViewModel,
    shownState: MutableState<Boolean>,
) {
    if (shownState.value) {
        AlertDialog(
            onDismissRequest = { shownState.value = false },
            title = { Text(stringResource(R.string.move_note_to_the_trash)) },
            text = { Text(stringResource(R.string.are_you_sure_you_want)) },
            confirmButton = {
                TextButton(onClick = { viewModel.moveToTrash(noteEntry) }) {
                    Text(stringResource(R.string.confirm))
                }
            },
            dismissButton = {
                TextButton(onClick = { shownState.value = false }) {
                    Text(stringResource(R.string.dismiss))
                }
            }
        )
    }
}