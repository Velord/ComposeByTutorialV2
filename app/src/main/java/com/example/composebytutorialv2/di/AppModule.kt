package com.example.composebytutorialv2.di

import android.content.Context
import androidx.room.Room
import com.example.composebytutorialv2.data.database.AppDatabase
import com.example.composebytutorialv2.data.database.DbMapper
import com.example.composebytutorialv2.data.database.DbMapperImpl
import com.example.composebytutorialv2.data.repository.Repository
import com.example.composebytutorialv2.data.repository.RepositoryImpl
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
        val postDao = database.postDao()

        return RepositoryImpl(dbMapper, noteDao, colorDao, postDao)
    }
}