package com.example.composebytutorialv2.ui.section2.chapter7

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.data.section2.model.NoteModel
import com.example.composebytutorialv2.ui.main.MainViewModel
import com.example.composebytutorialv2.ui.section2.chapter5.NoteListItemView

@Composable
fun NoteCollectionScreen(viewModel: MainViewModel) {
    val notes: List<NoteModel> by viewModel.noteCollection.collectAsState(emptyList())

    Column {
        JetNoteTopAppBarView(
            title = stringResource(R.string.jet_notes),
            icon = Icons.Filled.List,
            onIconClick = {}
        )
        NotesListView(notes = notes,
            onNoteClick = viewModel::noteClick,
            onNoteCheckedChange = viewModel::noteCheckedChange
        )
    }
}

@Composable
private fun NotesListView(
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

@Preview
@Composable
private fun NoteListViewPreview() {
    NotesListView(
        notes = listOf(
            NoteModel(1, "Title 1", "Content 1", null),
            NoteModel(1, "Title 2", "Content 2", true),
            NoteModel(1, "Title 3", "Content 3", false)
        ),
        onNoteClick = {}, onNoteCheckedChange = {}
    )
}
