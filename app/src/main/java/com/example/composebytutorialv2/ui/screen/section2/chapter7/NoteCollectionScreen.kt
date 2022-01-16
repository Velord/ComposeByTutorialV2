package com.example.composebytutorialv2.ui.screen.section2.chapter7

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.data.model.section2.NoteModel
import com.example.composebytutorialv2.ui.component.section2.chapter5.NoteListItemView
import com.example.composebytutorialv2.ui.component.section2.chapter7.JetNoteTopAppBarView
import com.example.composebytutorialv2.ui.screen.section2.NotesViewModel

@ExperimentalMaterialApi
@Composable
fun NoteCollectionScreen(
    viewModel: NotesViewModel,
    drawTopBar: Boolean = false
) {
    val notes: List<NoteModel> by viewModel.noteCollection.collectAsState(emptyList())

    Column {
        if (drawTopBar) {
            JetNoteTopAppBarView(
                title = stringResource(R.string.jet_notes),
                icon = Icons.Filled.List,
                onIconClick = {}
            )
        }
        NoteListView(
            notes = notes,
            onNoteClick = viewModel::noteClick,
            onNoteCheckedChange = viewModel::noteCheckedChange
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun NoteListView(
    notes: List<NoteModel>,
    onNoteClick: (NoteModel) -> Unit,
    onNoteCheckedChange: (NoteModel) -> Unit
) {
    LazyColumn {
        items(notes) {
            NoteListItemView(
                note = it,
                onNoteClick = onNoteClick,
                onNoteCheckedChange = onNoteCheckedChange
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
private fun NoteListViewPreview() {
    NoteListView(
        notes = listOf(
            NoteModel(1, "Title 1", "Content 1", null),
            NoteModel(1, "Title 2", "Content 2", true),
            NoteModel(1, "Title 3", "Content 3", false)
        ),
        onNoteClick = {}, onNoteCheckedChange = {}
    )
}
