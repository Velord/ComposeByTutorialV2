package com.example.composebytutorialv2.data.database.dao.section2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.composebytutorialv2.data.database.model.section2.ColorDb
import kotlinx.coroutines.flow.Flow

@Dao
interface ColorDao {

    @Query("SELECT * FROM ColorDb")
    fun getAll(): Flow<List<ColorDb>>

    @Query("SELECT * FROM ColorDb")
    fun getAllSync(): List<ColorDb>

    @Query("SELECT * FROM ColorDb WHERE id LIKE :id")
    fun findById(id: Long): Flow<ColorDb>

    @Query("SELECT * FROM ColorDb WHERE id LIKE :id")
    fun findByIdSync(id: Long): ColorDb

    @Insert
    fun insertAll(vararg colorDbs: ColorDb)
}