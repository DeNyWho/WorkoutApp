package com.example.workoutapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.workoutapp.domain.model.WorkoutRemoteKeys

@Dao
interface WorkoutRemoteKeysDao {

    @Query("SELECT * FROM workout_remote_keys_table WHERE id =:workoutId")
    suspend fun getRemoteKeys(workoutId: Int): WorkoutRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(workoutRemoteKeys: List<WorkoutRemoteKeys>)

    @Query("DELETE FROM workout_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}