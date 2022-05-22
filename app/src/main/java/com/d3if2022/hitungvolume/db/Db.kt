package com.d3if2022.hitungvolume.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Entity::class], version = 1, exportSchema = false)
abstract class Db : RoomDatabase() {

    abstract val dao: Dao

    companion object {
        @Volatile
        private var INSTANCE: Db? = null
        fun getInstance(context: Context): Db {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        Db::class.java,
                        "db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}