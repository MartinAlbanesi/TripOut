package com.example.turistaapp.main

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.turistaapp.core.NavHostScreen
import com.example.turistaapp.core.utils.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel()
) {

    val navController = rememberNavController()
    val index by mainViewModel.indexSelect.observeAsState(1)
    val title by mainViewModel.titleAppBar.observeAsState("Home")
    val route by mainViewModel.route.observeAsState(Routes.Home.route)

    val showBottomBar by mainViewModel.showBottomBar.observeAsState(true)
    val showFloatingActionButton by mainViewModel.showFloatingActionButton.observeAsState(true)

    Scaffold(
        topBar = {
            TopBarScreen(
                title = title,
                onClickNavigation = {
                    navController.navigate(route)
                }
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
                FloatingScreen {
                    navController.navigate(Routes.CreateTrip.route)
                    mainViewModel.setTitle("Crea y Viaja")
                    mainViewModel.setShowFloatingActionButton(false)
                    mainViewModel.setShowBottomBar(false)
                    mainViewModel.setRoute(Routes.Home.route)
                }
            }
        },
    ) { paddingValues ->
        NavHostScreen(navController = navController, paddingValues)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarScreen(
    title: String,
    iconsNavigation: ImageVector = Icons.Outlined.ArrowBack,
    iconsAction: List<ImageVector> = emptyList(),
    onClickNavigation: () -> Unit,
) {

    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = iconsNavigation, contentDescription = null)
            }
        },
        actions = {
            iconsAction.forEach { icon ->
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            }
        }
    )
}

@Composable
fun FloatingScreen(onClickButton: () -> Unit) {
    FloatingActionButton(onClick = { onClickButton() }) {
        Icon(Icons.Default.Add, contentDescription = "Add")
    }
}

@Composable
fun BottomBarScreen(
    index: Int,
    navController: NavHostController = rememberNavController(),
    changeIndex: (Int) -> Unit
) {

    NavigationBar {

        NavigationBarItem(
            selected = index == 1,
            onClick = {
                navController.navigate(Routes.Home.route)
                changeIndex(1)
            },
            icon = {
                Icon(Icons.Outlined.Home, contentDescription = "Home")
            }
        )
        NavigationBarItem(
            selected = index == 2,
            onClick = {
                navController.navigate(Routes.Trips.route)
                changeIndex(2)
            },
            icon = {
                Icon(Icons.Outlined.DirectionsCar, contentDescription = "Viajes")
            }
        )

        NavigationBarItem(
            selected = index == 3,
            onClick = {
                navController.navigate(Routes.Settings.route)
                changeIndex(3)
            },
            icon = {
                Icon(Icons.Outlined.Settings, contentDescription = "Configuraciones")
            }
        )

    }
}