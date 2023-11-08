package com.example.turistaapp.core.ui

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import com.example.turistaapp.home.ui.HomeViewModel
import com.example.turistaapp.home.ui.ShakeGameScreen
import com.example.turistaapp.welcome.ui.WelcomeScreen
import com.example.turistaapp.welcome.ui.WelcomeViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@Composable
fun NavHostScreen(
    welcomeViewModel: WelcomeViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
    onClickChangeTheme: () -> Unit
) {
    val navController = rememberNavController()

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            homeViewModel.getNearbyLocationsWithLastLocation()
            navController.navigate(Routes.Home.route)
        } else {
            navController.navigate(Routes.Home.route)
        }
    }


    val name by welcomeViewModel.name.collectAsStateWithLifecycle()

    val starDestination = if (name != null) Routes.Home.route else Routes.Welcome.route

    NavHost(navController = navController, startDestination = starDestination) {
        composable(Routes.Home.route) {
            MainScreen(navController = navController) {
                onClickChangeTheme()
            }
        }
        composable(
            Routes.CreateTrip.route,
            arguments = listOf(navArgument("address") { defaultValue = "" })
        ) {
            CreateTripScreen(address = it.arguments?.getString("address")) {
                navController.navigate(Routes.Home.route)
            }
        }
        composable(Routes.Welcome.route) {
            WelcomeScreen(onClickSaveName = {
                welcomeViewModel.setNameInDataStore(it)
                launcher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
            })
        }
        composable(Routes.ShakeGame.route){
            ShakeGameScreen(
                onCreateTripDialog = { navController.navigate(Routes.CreateTrip.setArgument(it)) },
                onNavigateToHome = { navController.navigate(Routes.Home.route) }
            )
        }
    }
}
