package com.example.stepsapp.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
abstract class DailyStepsDao {

    @Query("""
        SELECT * FROM daily_steps
        WHERE date = :date
    """)
    abstract fun observeDay(date: String): Flow<DailySteps?>

    @Query("""
        UPDATE daily_steps
        SET steps = steps + 1
        WHERE date = :date
    """)
    protected abstract suspend fun incrementExisting(
        date: String
    ): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    protected abstract suspend fun insert(
        record: DailySteps
    )

    @Transaction
    open suspend fun incrementSteps(date: String) {
        val updated = incrementExisting(date)

        if (updated == 0) {
            insert(DailySteps(date = date, steps = 1))
        }
    }
}