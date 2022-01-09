package com.example.composebytutorialv2.data.section2.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.composebytutorialv2.data.section2.database.model.NoteDb
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM NoteDb")
    fun getAllSync(): List<NoteDb>

    @Query("SELECT * FROM NoteDb WHERE id IN (:noteIds)")
    fun getNotesByIdsSync(noteIds: List<Long>): List<NoteDb>

    @Query("SELECT * FROM NoteDb WHERE id LIKE :id")
    fun findById(id: Long): Flow<NoteDb>

    @Query("SELECT * FROM NoteDb WHERE id LIKE :id")
    fun findByIdSync(id: Long): NoteDb

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(noteDb: NoteDb)

    @Insert
    fun insertAll(vararg noteDb: NoteDb)

    @Query("DELETE FROM NoteDb WHERE id LIKE :id")
    fun delete(id: Long)

    @Query("DELETE FROM NoteDb WHERE id IN (:noteIds)")
    fun delete(noteIds: List<Long>)
}