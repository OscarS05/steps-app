package com.example.stepsapp.models

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DailySteps::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dailyStepsDao(): DailyStepsDao
}