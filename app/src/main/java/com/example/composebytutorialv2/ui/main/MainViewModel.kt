package com.example.composebytutorialv2.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composebytutorialv2.data.section2.model.NoteModel
import com.example.composebytutorialv2.data.section2.repository.Repository
import com.example.composebytutorialv2.navigation.JetNoteRouter
import com.example.composebytutorialv2.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val noteCollection by lazy {
        repository.getAllNotesFlow()
    }

    val noteEntry: MutableStateFlow<NoteModel> = MutableStateFlow(NoteModel())

    val allColors by lazy {
        repository.getAllColors()
    }

    fun createNewNote() {
        noteEntry.update { NoteModel() }
        JetNoteRouter.navigateTo(Screen.Section2.SaveNote)
    }

    fun noteClick(note: NoteModel) {
        noteEntry.update { note }
        JetNoteRouter.navigateTo(Screen.Section2.SaveNote)
    }

    fun noteCheckedChange(note: NoteModel) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.insertOrReplaceNote(note)
        }
    }

    fun noteEntryChange(note: NoteModel) {
        noteEntry.update { note }
    }

    fun saveNote(note: NoteModel) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.insertOrReplaceNote(note)
            JetNoteRouter.navigateTo(Screen.Section2.EntryPointNote)
            noteEntry.update { NoteModel() }
        }
    }

    fun moveToTrash(note: NoteModel) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.moveNoteToTrash(note.id)
            JetNoteRouter.navigateTo(Screen.Section2.EntryPointNote)
        }
    }
}