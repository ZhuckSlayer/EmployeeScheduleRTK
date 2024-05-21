package com.example.employeeschedulertk.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [EmployeeDbModel::class], version = 1, exportSchema = false)
abstract class AppDataBase:RoomDatabase() {
    companion object {
        private const val DB_NAME = "table.db"
        private var Instance: AppDataBase? = null
        private val LOCK = Any()

        fun getInstance(application: Application): AppDataBase {
            Instance?.let {
                return it
            }

            synchronized(LOCK) {
                Instance?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDataBase::class.java,
                    DB_NAME
                )
                    .build()
                Instance = db
                return db
            }


        }
    }
    abstract fun employeeListDao():EmployeeListDao
}