package com.example.workoutapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.workoutapp.data.local.dao.WorkoutDao
import com.example.workoutapp.data.local.dao.WorkoutRemoteKeysDao
import com.example.workoutapp.domain.model.Workout
import com.example.workoutapp.domain.model.WorkoutRemoteKeys

@Database(entities = [Workout::class, WorkoutRemoteKeys::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class WorkoutDatabase: RoomDatabase(){

    abstract fun workoutDao(): WorkoutDao

    abstract fun workoutRemoteKeysDao(): WorkoutRemoteKeysDao
}