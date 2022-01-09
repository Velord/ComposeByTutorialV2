package com.example.composebytutorialv2.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composebytutorialv2.data.section2.model.NoteModel
import com.example.composebytutorialv2.data.section2.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val noteCollection by lazy {
        repository.getAllNotesFlow()
    }

    fun createNewNote() {
        //TODO()
    }

    fun noteClick(note: NoteModel) {
        //TODO()
    }

    fun noteCheckedChange(note: NoteModel) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.insertNote(note)
        }
    }
}