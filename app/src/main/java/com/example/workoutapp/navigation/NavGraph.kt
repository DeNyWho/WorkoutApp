package com.example.workoutapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.workoutapp.presentation.screens.home.HomeScreen
import com.example.workoutapp.presentation.screens.splash.SplashScreen
import com.example.workoutapp.presentation.screens.welcome.WelcomeScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ){

        composable(route = Screen.Splash.route){
            SplashScreen(navController = navController)
        }

        composable(route = Screen.Welcome.route){
            WelcomeScreen(navController = navController)
        }

        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
    }
    
}