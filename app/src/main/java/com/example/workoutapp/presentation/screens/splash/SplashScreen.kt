package com.example.workoutapp.presentation.screens.splash

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.workoutapp.R
import com.example.workoutapp.navigation.Screen
import com.example.workoutapp.ui.theme.Purple500
import com.example.workoutapp.ui.theme.Purple700

@Composable
fun SplashScreen(
    navController: NavController,
//    splashViewModel: SplashViewModel
) {

//    val onBoardingCompleted by splashViewModel.onBoardingCompleted.collectAsState()
    
//    LaunchedEffect(key1 = true){
//
//        navController.popBackStack()
//        if(onBoardingCompleted) {
//            navController.navigate(Screen.Home.route)
//        } else {
//            navController.navigate(Screen.Welcome.route)
//        }
//    }

    Splash()

    
}

@Composable
fun Splash() {

    if(isSystemInDarkTheme()){
        Box(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.logo)
            )
        }
    } else {
        Box(
            modifier = Modifier
                .background(Brush.verticalGradient(listOf(Purple700, Purple500)))
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.logo)
            )
        }
    }
}

@Preview
@Composable
fun SplashPrevDay() {
    Splash()
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SplashPrevNight() {
    Splash()
}