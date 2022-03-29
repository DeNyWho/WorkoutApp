package com.example.workoutapp.domain.repository

import androidx.paging.PagingData
import com.example.workoutapp.domain.model.Workout
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getAllWorkouts(): Flow<PagingData<Workout>>

    fun searchWorkout(): Flow<PagingData<Workout>>
}