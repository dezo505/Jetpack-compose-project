package com.example.jetpackcomposeproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.jetpackcomposeproject.data.dao.AthleteDAO
import com.example.jetpackcomposeproject.data.model.Athlete

@Database(entities = [Athlete::class], version = 1)
abstract class AthleteDatabase: RoomDatabase() {

    abstract fun athleteDAO(): AthleteDAO?

    companion object {
        private var DB_INSTANCE: AthleteDatabase? = null
        @Synchronized
        fun getDatabase(context: Context): AthleteDatabase? {
            if (DB_INSTANCE == null) {
                DB_INSTANCE = databaseBuilder(context.applicationContext,
                    AthleteDatabase::class.java,
                    "athlete_database")
                    .allowMainThreadQueries()
                    .build()
            }
            return DB_INSTANCE
        }
    }
}