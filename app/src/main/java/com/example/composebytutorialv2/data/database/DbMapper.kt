package com.example.composebytutorialv2.data.database

import com.example.composebytutorialv2.data.database.model.section2.ColorDb
import com.example.composebytutorialv2.data.database.model.section2.NoteDb
import com.example.composebytutorialv2.data.database.model.section3.PostDbModel
import com.example.composebytutorialv2.data.model.section2.ColorModel
import com.example.composebytutorialv2.data.model.section2.NEW_NOTE_ID
import com.example.composebytutorialv2.data.model.section2.NoteModel
import com.example.composebytutorialv2.data.model.section3.PostModel
import com.example.composebytutorialv2.data.model.section3.PostType
import java.util.concurrent.TimeUnit

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
    //NoteModel -> NoteDb
    fun mapDbNote(noteModel: NoteModel): NoteDb
    //ColorModel -> ColorDb
    fun mapDbColors(colorModels: List<ColorModel>): List<ColorDb>
    fun mapDbColor(colorModel: ColorModel): ColorDb
    //PostDb -> PostModel
    fun mapPost(dbPostDbModel: PostDbModel): PostModel
    //PostModel -> PostDb
    fun mapDbPost(postModel: PostModel): PostDbModel
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

    override fun mapPost(dbPostDbModel: PostDbModel): PostModel {
        with(dbPostDbModel) {
            return PostModel(
                username,
                subreddit,
                title,
                text,
                likes.toString(),
                comments.toString(),
                PostType.fromType(type),
                getPostedDate(datePosted),
                image
            )
        }
    }

    override fun mapDbPost(postModel: PostModel): PostDbModel {
        with(postModel) {
            return PostDbModel(
                null,
                "raywenderlich",
                subreddit,
                title,
                text,
                0,
                0,
                type.type,
                System.currentTimeMillis(),
                false,
                image
            )
        }
    }

    private fun getPostedDate(date: Long): String {
        val hoursPassed = TimeUnit.HOURS
            .convert(System.currentTimeMillis() - date, TimeUnit.MILLISECONDS)
        if (hoursPassed > 24) {
            val daysPassed = TimeUnit.DAYS.convert(hoursPassed, TimeUnit.HOURS)

            if (daysPassed > 30) {
                if (daysPassed > 365) {
                    return (daysPassed / 365).toString() + "y"
                }
                return (daysPassed / 30).toString() + "mo"

            }
            return daysPassed.toString() + "d"
        }

        return hoursPassed.inc().toString() + "h"
    }
}