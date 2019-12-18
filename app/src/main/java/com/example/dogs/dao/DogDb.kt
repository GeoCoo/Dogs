package com.example.dogs.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dogs.model.DogModel
import java.security.AccessControlContext

@Database(entities = arrayOf(DogModel::class),version = 1)
abstract class DogDb : RoomDatabase() {
    abstract fun dogDao(): DogDao

    companion object {
        @Volatile
        private var instance: DogDb? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDb(context).also {
                instance = it
            }
        }


        private fun buildDb(context: Context) =
            Room.databaseBuilder(context.applicationContext, DogDb::class.java, "dogdb").build()
    }
}