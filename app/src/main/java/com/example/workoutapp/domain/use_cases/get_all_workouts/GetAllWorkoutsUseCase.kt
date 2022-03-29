package com.example.workoutapp.domain.use_cases.get_all_workouts

import androidx.paging.PagingData
import com.example.workoutapp.data.repository.Repository
import com.example.workoutapp.domain.model.Workout
import kotlinx.coroutines.flow.Flow

class GetAllWorkoutsUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Workout>> {
        return repository.getAllWorkouts()
    }
}