package com.example.turistaapp.main

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.turistaapp.R
import com.example.turistaapp.home.ui.HomeViewModel
import com.example.turistaapp.utils.NavHostScreen
import com.example.turistaapp.utils.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(homeViewModel: HomeViewModel) {

    val navController = rememberNavController()
    val index by homeViewModel.indexSelect.observeAsState(1)

    Scaffold(
        bottomBar = {
            BottomBarScreen(
                index,
                navController,
                changeIndex = { homeViewModel.setIndex(it) }
            )
        },
        floatingActionButton = {
            FloatingScreen {}
        },
    ) { paddingValues ->
        NavHostScreen(navController = navController, paddingValues)
    }
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
                Icon(imageVector = Icons.Outlined.Home, contentDescription = "Home")
            }
        )
        NavigationBarItem(
            selected = index == 2,
            onClick = {
                navController.navigate(Routes.Viajes.route)
                changeIndex(2)
            },
            icon = {
                Icon(painter = painterResource(id = R.drawable.car), contentDescription = "Viajes")
            }
        )

        NavigationBarItem(
            selected = index == 3,
            onClick = {
                navController.navigate(Routes.Configuraciones.route)
                changeIndex(3)
            },
            icon = {
                Icon(imageVector = Icons.Outlined.Settings, contentDescription = "Configuraciones")
            }
        )

    }
}