package com.example.composebytutorialv2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composebytutorialv2.data.database.dao.section2.ColorDao
import com.example.composebytutorialv2.data.database.dao.section2.NoteDao
import com.example.composebytutorialv2.data.database.dao.section3.PostDao
import com.example.composebytutorialv2.data.database.model.section2.ColorDb
import com.example.composebytutorialv2.data.database.model.section2.NoteDb
import com.example.composebytutorialv2.data.database.model.section3.PostDbModel

@Database(entities = [NoteDb::class, ColorDb::class, PostDbModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "note-maker-database"
    }

    abstract fun noteDao(): NoteDao

    abstract fun colorDao(): ColorDao

    abstract fun postDao(): PostDao
}