package com.example.workoutapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.workoutapp.domain.model.Workout

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM workout_table ORDER BY id ASC")
    fun getAllWorkouts(): PagingSource<Int, Workout>

    @Query("SELECT * FROM workout_table WHERE id =:workoutId")
    fun getSelectedWorkout(workoutId: Int): Workout

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWorkout(workouts: List<Workout>)

    @Query("DELETE FROM workout_table")
    suspend fun deleteAllWorkouts()
}