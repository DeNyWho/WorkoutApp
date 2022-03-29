package com.example.workoutapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val Colors.welcomeScreenBackgroundColor
    @Composable
    get() = if(isLight) Color.White else Color.Black

val Colors.activeIndicatorColor
    @Composable
    get() = if(isLight) Purple500 else Purple700

val Colors.inactiveIndicatorColor
    @Composable
    get() = if(isLight) LightGray else DarkGray