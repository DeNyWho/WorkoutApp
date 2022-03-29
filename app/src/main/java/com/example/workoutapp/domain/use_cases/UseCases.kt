package com.example.workoutapp.domain.use_cases

import com.example.workoutapp.domain.use_cases.get_all_workouts.GetAllWorkoutsUseCase
import com.example.workoutapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.example.workoutapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllWorkoutUseCase: GetAllWorkoutsUseCase
)