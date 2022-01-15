package com.example.composebytutorialv2.data.model.section2

const val NEW_NOTE_ID = -1L

data class NoteModel(
    val id: Long = NEW_NOTE_ID, // This value is used for new notes
    val title: String = "",
    val content: String = "",
    //null represents that the note can't be checked off
    val isCheckedOff: Boolean? = null,
    val color: ColorModel = ColorModel.DEFAULT
)