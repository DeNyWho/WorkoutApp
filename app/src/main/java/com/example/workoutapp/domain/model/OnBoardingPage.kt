package com.example.workoutapp.domain.model

import androidx.annotation.DrawableRes
import com.example.workoutapp.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String,
) {

    object First: OnBoardingPage(
        image = R.drawable.test,
        title = "Workout Anywhere",
        description = "Choose your preferred location and\nDo your workouts anytime that suits\n You"
    )

    object Second: OnBoardingPage(
        image = R.drawable.m_m_firstonboarding,
        title = "Track your Progress",
        description = "Check yourself at Each Workout Phase\nAnd update your Fitness Profile"
    )

    object Third: OnBoardingPage(
        image = R.drawable.m_m_firstonboarding,
        title = "Stay Strong & Healthy",
            description = "We want you to stay Strong and healthy,\nSo we have provided a Diet plan for you.\nEnjoy!"
    )


}
