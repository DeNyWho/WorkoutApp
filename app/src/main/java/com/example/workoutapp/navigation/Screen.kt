package com.example.workoutapp.navigation

sealed class Screen(val route: String){
    object Splash: Screen("splash_screen")
    object Welcome: Screen("welcome_screen")
    object Home: Screen("home_screen")
    object Details: Screen("details_screen/{workoutId}"){
        fun passWorkoutId(workoutId: Int): String {
            return "details_screen/$workoutId"
        }
    }
    object Search: Screen("search_screen")
}
