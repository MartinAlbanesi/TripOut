package com.example.turistaapp.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.turistaapp.core.utils.enums.Routes
import com.example.turistaapp.create_trip.ui.screens.CreateTripScreen
import com.example.turistaapp.welcome.ui.WelcomeScreen
import com.example.turistaapp.welcome.ui.WelcomeViewModel

@Composable
fun NavHostScreen(
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    val name by welcomeViewModel.name.collectAsStateWithLifecycle()

    val starDestination = if (name != null) Routes.Home.route else  Routes.Welcome.route

    NavHost(navController = navController, startDestination = starDestination) {
        composable(Routes.Home.route) {
            MainScreen(navController = navController)
        }
        composable(
            Routes.CreateTrip.route,
            arguments = listOf(navArgument("address"){defaultValue = ""})
        ) {
            CreateTripScreen(address = it.arguments?.getString("address")) {
                navController.navigate(Routes.Home.route)
            }
        }
        composable(Routes.Welcome.route) {
            WelcomeScreen(onClickSaveName = {
                welcomeViewModel.setNameInDataStore(it)
                navController.navigate(Routes.Home.route)
            })
        }
    }
}
