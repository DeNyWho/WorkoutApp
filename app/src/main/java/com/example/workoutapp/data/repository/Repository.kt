package com.example.workoutapp.data.repository

import androidx.paging.PagingData
import com.example.workoutapp.domain.model.Workout
import com.example.workoutapp.domain.repository.DataStoreOperations
import com.example.workoutapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remote: RemoteDataSource,
    private val dataStore: DataStoreOperations,
) {

    fun getAllWorkouts(): Flow<PagingData<Workout>> {
        return remote.getAllWorkouts()
    }

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }
}