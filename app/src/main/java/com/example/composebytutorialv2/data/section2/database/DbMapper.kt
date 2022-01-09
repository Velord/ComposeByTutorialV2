package com.example.composebytutorialv2.data.section2.database

import com.example.composebytutorialv2.data.section2.database.model.ColorDb
import com.example.composebytutorialv2.data.section2.database.model.NoteDb
import com.example.composebytutorialv2.data.section2.model.ColorModel
import com.example.composebytutorialv2.data.section2.model.NEW_NOTE_ID
import com.example.composebytutorialv2.data.section2.model.NoteModel

//NoteDb -> Note
interface DbMapper {
    fun mapNotes(
        noteDbs: List<NoteDb>,
        colorDbs: Map<Long, ColorDb>
    ): List<NoteModel>
    fun mapNote(noteDb: NoteDb, colorDb: ColorDb): NoteModel
    //ColorDb -> Color
    fun mapColors(colorDbs: List<ColorDb>): List<ColorModel>
    fun mapColor(colorDb: ColorDb): ColorModel
    //Note -> NoteDb
    fun mapDbNote(noteModel: NoteModel): NoteDb
    //Color -> ColorDb
    fun mapDbColors(colorModels: List<ColorModel>): List<ColorDb>
    fun mapDbColor(colorModel: ColorModel): ColorDb
}

class DbMapperImpl : DbMapper {

    override fun mapNotes(
        noteDbs: List<NoteDb>,
        colorDbs: Map<Long, ColorDb>
    ): List<NoteModel> = noteDbs.map {
        val colorDb = colorDbs[it.colorId] ?: throw RuntimeException(
            "Color for colorId: ${it.colorId} was not found. " +
                    "Make sure that all colors are passed to this method"
        )

        mapNote(it, colorDb)
    }

    override fun mapNote(noteDb: NoteDb, colorDb: ColorDb): NoteModel {
        val color = mapColor(colorDb)
        val isCheckedOff = with(noteDb) { if (canBeCheckedOff) isCheckedOff else null }
        return with(noteDb) { NoteModel(id, title, content, isCheckedOff, color) }
    }

    override fun mapColors(colorDbs: List<ColorDb>): List<ColorModel> = colorDbs.map { mapColor(it) }

    override fun mapColor(colorDb: ColorDb): ColorModel = with(colorDb) { ColorModel(id, name, hex) }

    override fun mapDbNote(noteModel: NoteModel): NoteDb = with(noteModel) {
        val canBeCheckedOff = isCheckedOff != null
        val isCheckedOff = isCheckedOff ?: false
        val newId = if (id == NEW_NOTE_ID) NoteDb.DEF_ID else id
        NoteDb(newId, title, content, canBeCheckedOff, isCheckedOff, color.id, false)
    }

    override fun mapDbColors(colorModels: List<ColorModel>): List<ColorDb> = colorModels.map { mapDbColor(it) }

    override fun mapDbColor(colorModel: ColorModel): ColorDb = with(colorModel) { ColorDb(id, hex, name) }
}