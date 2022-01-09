package com.example.composebytutorialv2.di

import android.content.Context
import androidx.room.Room
import com.example.composebytutorialv2.data.section2.database.DbMapper
import com.example.composebytutorialv2.data.section2.database.DbMapperImpl
import com.example.composebytutorialv2.data.section2.repository.Repository
import com.example.composebytutorialv2.data.section2.repository.RepositoryImpl
import com.example.composebytutorialv2.ui.section2.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDbMapper(): DbMapper = DbMapperImpl()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideRepository(database: AppDatabase, dbMapper: DbMapper): Repository {
        val noteDao = database.noteDao()
        val colorDao = database.colorDao()

        return RepositoryImpl(noteDao, colorDao, dbMapper)
    }
}