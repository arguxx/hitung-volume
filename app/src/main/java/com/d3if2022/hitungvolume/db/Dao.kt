package com.d3if2022.hitungvolume.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
    @Insert
    fun insert(mobpro: Entity)

    @Query("SELECT * FROM mobpro ORDER BY id DESC")
    fun getLastData(): LiveData<List<Entity?>>

    @Query("DELETE FROM mobpro")
    fun clearData()

    @Delete
    fun delete(mobpro: Entity)
}