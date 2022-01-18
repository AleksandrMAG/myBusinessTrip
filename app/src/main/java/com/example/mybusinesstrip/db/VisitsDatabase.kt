package com.example.mybusinesstrip.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mybusinesstrip.db.dao.VisitsDao
import com.example.mybusinesstrip.model.VisitsModel

@Database(entities = [VisitsModel::class], version = 1)
abstract class VisitsDatabase : RoomDatabase() {

    abstract fun getVisitsDao(): VisitsDao

    companion object {
        const val DBNAME = "visits_dbase.db"

        private var database: VisitsDatabase? = null

        @Synchronized
        fun getInstance(context: Context) : VisitsDatabase {
            return if (database == null) {
                database = Room.databaseBuilder(context, VisitsDatabase::class.java, DBNAME).build()
                database as VisitsDatabase
            } else {
                database as VisitsDatabase
            }
        }
    }

}