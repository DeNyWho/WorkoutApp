package com.example.workoutapp.di

import android.content.Context
import androidx.room.Room
import com.example.workoutapp.data.local.WorkoutDatabase
import com.example.workoutapp.util.Constants.WORKOUT_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): WorkoutDatabase {
        return Room.databaseBuilder (
            context,
            WorkoutDatabase::class.java,
            WORKOUT_DATABASE,
        ).build()
    }
}