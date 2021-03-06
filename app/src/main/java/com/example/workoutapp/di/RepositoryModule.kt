package com.example.workoutapp.di

import android.content.Context
import com.example.workoutapp.data.repository.DataStoreOperationsImpl
import com.example.workoutapp.data.repository.Repository
import com.example.workoutapp.domain.repository.DataStoreOperations
import com.example.workoutapp.domain.use_cases.UseCases
import com.example.workoutapp.domain.use_cases.get_all_workouts.GetAllWorkoutsUseCase
import com.example.workoutapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.example.workoutapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(
        repository: Repository
    ): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository = repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository = repository),
            getAllWorkoutUseCase = GetAllWorkoutsUseCase(repository = repository)
        )
    }
}