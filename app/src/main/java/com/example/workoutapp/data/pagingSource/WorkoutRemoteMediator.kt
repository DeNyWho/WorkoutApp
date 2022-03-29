package com.example.workoutapp.data.pagingSource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.workoutapp.data.local.WorkoutDatabase
import com.example.workoutapp.data.remote.WorkoutApi
import com.example.workoutapp.domain.model.Workout
import com.example.workoutapp.domain.model.WorkoutRemoteKeys
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class WorkoutRemoteMediator @Inject constructor(
    private val WorkoutApi: WorkoutApi,
    private val WorkoutDatabase: WorkoutDatabase
): RemoteMediator<Int, Workout>() {

    private val workoutDao = WorkoutDatabase.workoutDao()
    private val workoutRemoteKeysDao = WorkoutDatabase.workoutRemoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Workout>): RemoteMediator.MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return RemoteMediator.MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return RemoteMediator.MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = WorkoutApi.getAllWorkouts(page = page)
            if (response.workouts.isNotEmpty()) {
                WorkoutDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        workoutDao.deleteAllWorkouts()
                        workoutRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.workouts.map { workout ->
                        WorkoutRemoteKeys(
                            id = workout.id,
                            prevPage = prevPage,
                            nextPage = nextPage
                        )
                    }
                    workoutRemoteKeysDao.addAllRemoteKeys(workoutRemoteKeys = keys)
                    workoutDao.addWorkout(workouts = response.workouts)
                }
            }
            RemoteMediator.MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (e: Exception) {
            return RemoteMediator.MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Workout>
    ): WorkoutRemoteKeys? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.id?.let { id ->
                workoutRemoteKeysDao.getRemoteKeys(workoutId = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Workout>
    ): WorkoutRemoteKeys? {
        return state.pages.firstOrNull{ it.data.isNotEmpty() } ?.data?.firstOrNull()
            ?.let {
                workoutRemoteKeysDao.getRemoteKeys(workoutId = it.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Workout>
    ): WorkoutRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() } ?.data?.lastOrNull()
            ?.let {
                workoutRemoteKeysDao.getRemoteKeys(workoutId = it.id)
            }
    }
}