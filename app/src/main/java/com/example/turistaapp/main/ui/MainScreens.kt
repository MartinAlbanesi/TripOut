package com.example.turistaapp.main.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.turistaapp.core.NavHostScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHostScreen(navController = navController)
}
