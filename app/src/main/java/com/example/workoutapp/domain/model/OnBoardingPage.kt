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
        image = R.drawable.healthy,
        title = "Stay Strong & Healthy",
        description = "We want you to stay Strong and healthy, So we have provided a Diet plan for you. Enjoy!"
    )

    object Second: OnBoardingPage(
        image = R.drawable.healthy,
        title = "Stay Strong & Healthy",
        description = "We want you to stay Strong and healthy, So we have provided a Diet plan for you. Enjoy!"
    )

    object Third: OnBoardingPage(
        image = R.drawable.healthy,
        title = "Stay Strong & Healthy",
        description = "We want you to stay Strong and healthy, So we have provided a Diet plan for you. Enjoy!"
    )


}
