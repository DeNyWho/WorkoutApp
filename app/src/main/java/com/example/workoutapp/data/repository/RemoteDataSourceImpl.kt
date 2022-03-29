package com.example.workoutapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.workoutapp.data.local.WorkoutDatabase
import com.example.workoutapp.data.pagingSource.WorkoutRemoteMediator
import com.example.workoutapp.data.remote.WorkoutApi
import com.example.workoutapp.domain.model.Workout
import com.example.workoutapp.domain.repository.RemoteDataSource
import com.example.workoutapp.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class RemoteDataSourceImpl (
    private val workoutApi: WorkoutApi,
    private val workoutDatabase: WorkoutDatabase
): RemoteDataSource {

    private val workoutDao = workoutDatabase.workoutDao()

    override fun getAllWorkouts(): Flow<PagingData<Workout>> {
        val pagingSourceFactory = { workoutDao.getAllWorkouts()}
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = WorkoutRemoteMediator(
                WorkoutApi = workoutApi,
                WorkoutDatabase = workoutDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchWorkout(): Flow<PagingData<Workout>> {
        TODO("Make search")
    }
}