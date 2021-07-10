package com.testosterolapp.letgoproject.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.testosterolapp.letgoproject.data.Character
import com.testosterolapp.letgoproject.data.Superhero

@androidx.room.Database(entities = [Superhero::class, Character::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun superheroDao(): SuperheroDao?


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: Database? = null

        fun getDatabase(context: Context): Database {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Database::class.java,
                    "db_repository"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}