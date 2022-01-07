package com.example.composebytutorialv2.ui.section2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composebytutorialv2.data.section2.database.model.ColorDb
import com.example.composebytutorialv2.data.section2.database.model.NoteDb
import com.example.composebytutorialv2.ui.section2.data.database.dao.ColorDao
import com.example.composebytutorialv2.ui.section2.data.database.dao.NoteDao

@Database(entities = [NoteDb::class, ColorDb::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "note-maker-database"
    }

    abstract fun noteDao(): NoteDao

    abstract fun colorDao(): ColorDao
}