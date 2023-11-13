package com.example.turistaapp.core.ui

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.IntOffset
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

@Composable
fun NavHostScreen(
    welcomeViewModel: WelcomeViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
    onClickChangeTheme: () -> Unit,
) {
    val navController = rememberNavController()

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission(),
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
            arguments = listOf(navArgument("address") { defaultValue = "" }),
            enterTransition = {
                slideIn(tween(500, easing = LinearOutSlowInEasing)) { fullSize ->
                    IntOffset(fullSize.width, fullSize.height)
                }
            },
            exitTransition = {
                slideOut(tween(200, easing = LinearOutSlowInEasing)) {
                    IntOffset(it.width, it.height)
                }
            },
            popEnterTransition = {
                slideIn(tween(500, easing = LinearOutSlowInEasing)) { fullSize ->
                    IntOffset(fullSize.width, fullSize.height)
                }
            },
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
        composable(
            Routes.ShakeGame.route,
            enterTransition = {
                slideInVertically(
                    animationSpec = tween(
                        durationMillis = 500,
                    ),
                )
            },
            exitTransition = {
                slideOutVertically(
                    animationSpec = tween(
                        durationMillis = 200,
                    ),
                    targetOffsetY = { -it },
                )
            },
            popEnterTransition = {
                slideInVertically(
                    animationSpec = tween(
                        durationMillis = 500,
                    ),
                )
            },
        ) {
            ShakeGameScreen(
                onCreateTripDialog = { navController.navigate(Routes.CreateTrip.setArgument(it)) },
                onNavigateToHome = { navController.navigate(Routes.Home.route) },
            )
        }
//        composable(
//            Routes.Map.route,
//            arguments = listOf(navArgument("TripID") { defaultValue = "0" }),
//            enterTransition = {
//                slideIn(tween(500, easing = LinearOutSlowInEasing)) { fullSize ->
//                    IntOffset(fullSize.width, fullSize.height)
//                }
//            },
//            exitTransition = {
//                slideOut(tween(200, easing = LinearOutSlowInEasing)) {
//                    IntOffset(it.width, it.height)
//                }
//            },
//            popEnterTransition = {
//                slideIn(tween(500, easing = LinearOutSlowInEasing)) { fullSize ->
//                    IntOffset(fullSize.width, fullSize.height)
//                }
//            }
//        ) {
//            MapScreen() {
//                navController.navigate(Routes.Home.route)
//            }
//        }
    }
}
