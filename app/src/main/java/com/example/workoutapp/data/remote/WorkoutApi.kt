package com.example.workoutapp.data.remote

import com.example.workoutapp.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WorkoutApi {

    @GET("workout/workouts")
    suspend fun getAllWorkouts(
        @Query("page") page: Int = 1
    ): ApiResponse

    @GET("workout/workouts/search")
    suspend fun searchWorkouts(
        @Query("title") title: String,
    ): ApiResponse
}