package com.example.composebytutorialv2.di.section2

import android.content.Context
import androidx.room.Room
import com.example.composebytutorialv2.data.section2.database.DbMapper
import com.example.composebytutorialv2.data.section2.database.DbMapperImpl
import com.example.composebytutorialv2.data.section2.repository.Repository
import com.example.composebytutorialv2.data.section2.repository.RepositoryImpl
import com.example.composebytutorialv2.ui.section2.data.database.AppDatabase

class DependencyInjector(applicationContext: Context) {

    val repository: Repository by lazy { provideRepository(database) }

    private val database: AppDatabase by lazy { provideDatabase(applicationContext) }

    private val dbMapper: DbMapper = DbMapperImpl()

    private fun provideDatabase(applicationContext: Context): AppDatabase =
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()

    private fun provideRepository(database: AppDatabase): Repository {
        val noteDao = database.noteDao()
        val colorDao = database.colorDao()

        return RepositoryImpl(noteDao, colorDao, dbMapper)
    }
}