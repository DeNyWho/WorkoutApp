package com.example.workoutapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.workoutapp.util.Constants.WORKOUT_REMOTE_KEYS_DATABASE_TABLE

@Entity(tableName = WORKOUT_REMOTE_KEYS_DATABASE_TABLE)
data class WorkoutRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val prevPage: Int?,
    val nextPage: Int?
)