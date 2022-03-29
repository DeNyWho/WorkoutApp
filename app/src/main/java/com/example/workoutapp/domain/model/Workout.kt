package com.example.workoutapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.workoutapp.util.Constants.WORKOUT_DATABASE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = WORKOUT_DATABASE_TABLE)
data class Workout(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val image: String,
    val about: String
)