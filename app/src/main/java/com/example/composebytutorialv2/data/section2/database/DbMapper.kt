package com.example.composebytutorialv2.data.section2.database

import com.example.composebytutorialv2.data.section2.database.model.ColorDb
import com.example.composebytutorialv2.data.section2.database.model.NoteDb
import com.example.composebytutorialv2.data.section2.model.Color
import com.example.composebytutorialv2.data.section2.model.NEW_NOTE_ID
import com.example.composebytutorialv2.data.section2.model.Note

//NoteDb -> Note
interface DbMapper {
    fun mapNotes(
        noteDbs: List<NoteDb>,
        colorDbs: Map<Long, ColorDb>
    ): List<Note>
    fun mapNote(noteDb: NoteDb, colorDb: ColorDb): Note
    //ColorDb -> Color
    fun mapColors(colorDbs: List<ColorDb>): List<Color>
    fun mapColor(colorDb: ColorDb): Color
    //Note -> NoteDb
    fun mapDbNote(note: Note): NoteDb
    //Color -> ColorDb
    fun mapDbColors(colors: List<Color>): List<ColorDb>
    fun mapDbColor(color: Color): ColorDb
}

class DbMapperImpl : DbMapper {

    override fun mapNotes(
        noteDbs: List<NoteDb>,
        colorDbs: Map<Long, ColorDb>
    ): List<Note> = noteDbs.map {
        val colorDb = colorDbs[it.colorId] ?: throw RuntimeException(
            "Color for colorId: ${it.colorId} was not found. " +
                    "Make sure that all colors are passed to this method"
        )

        mapNote(it, colorDb)
    }

    override fun mapNote(noteDb: NoteDb, colorDb: ColorDb): Note {
        val color = mapColor(colorDb)
        val isCheckedOff = with(noteDb) { if (canBeCheckedOff) isCheckedOff else null }
        return with(noteDb) { Note(id, title, content, isCheckedOff, color) }
    }

    override fun mapColors(colorDbs: List<ColorDb>): List<Color> = colorDbs.map { mapColor(it) }

    override fun mapColor(colorDb: ColorDb): Color = with(colorDb) { Color(id, name, hex) }

    override fun mapDbNote(note: Note): NoteDb = with(note) {
        val canBeCheckedOff = isCheckedOff != null
        val isCheckedOff = isCheckedOff ?: false
        val newId = if (id == NEW_NOTE_ID) NoteDb.DEF_ID else id
        NoteDb(newId, title, content, canBeCheckedOff, isCheckedOff, color.id, false)
    }

    override fun mapDbColors(colors: List<Color>): List<ColorDb> = colors.map { mapDbColor(it) }

    override fun mapDbColor(color: Color): ColorDb = with(color) { ColorDb(id, hex, name) }
}