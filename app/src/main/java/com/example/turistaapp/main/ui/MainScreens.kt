package com.example.turistaapp.main.ui

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.turistaapp.core.NavHostScreen
import com.example.turistaapp.core.utils.Routes
import com.example.turistaapp.main.MainViewModel
import com.example.turistaapp.main.ui.components.BottomBarScreen
import com.example.turistaapp.main.ui.components.FloatingActionButtonScreen
import com.example.turistaapp.main.ui.components.TopAppBarScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(mainViewModel: MainViewModel = hiltViewModel()) {

    val navController = rememberNavController()
    val index by mainViewModel.indexSelect.observeAsState(1)
    val title by mainViewModel.titleAppBar.observeAsState("Home")

    val showBottomBar by mainViewModel.showBottomBar.observeAsState(true)
    val showFloatingActionButton by mainViewModel.showFloatingActionButton.observeAsState(true)

    Scaffold(
        topBar = {
            TopAppBarScreen(
                title = title
            )
        },
        bottomBar = {
            if(showBottomBar){
                BottomBarScreen(
                    index,
                    navController,
                    changeIndex = { mainViewModel.setIndex(it) }
                )
            }
        },
        floatingActionButton = {
            if(showFloatingActionButton){
                FloatingActionButtonScreen {
                    navController.navigate(Routes.CreateTrip.route)
                    mainViewModel.setTitle("Crea y Viaja")
                    mainViewModel.setShowFloatingActionButton(false)
                    mainViewModel.setShowBottomBar(false)
                }
            }
        },
    ) { paddingValues ->
        NavHostScreen(navController = navController, paddingValues)
    }
}