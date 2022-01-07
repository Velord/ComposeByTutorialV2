package com.example.composebytutorialv2.ui.section2.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.composebytutorialv2.data.section2.database.model.ColorDb

@Dao
interface ColorDao {

    @Query("SELECT * FROM ColorDb")
    fun getAll(): LiveData<List<ColorDb>>

    @Query("SELECT * FROM ColorDb")
    fun getAllSync(): List<ColorDb>


    @Query("SELECT * FROM ColorDb WHERE id LIKE :id")
    fun findById(id: Long): LiveData<ColorDb>

    @Query("SELECT * FROM ColorDb WHERE id LIKE :id")
    fun findByIdSync(id: Long): ColorDb

    @Insert
    fun insertAll(vararg colorDbs: ColorDb)
}