package com.d3if2022.hitungvolume.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mobpro") data class Entity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var masukan: Float,
    var volume: Float,
    var luas: Float
)