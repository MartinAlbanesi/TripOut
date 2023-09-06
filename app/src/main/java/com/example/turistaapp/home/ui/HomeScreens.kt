package com.example.turistaapp.home.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.turistaapp.R
import com.example.turistaapp.map.ui.GoogleMapScreen
import com.example.turistaapp.ui.theme.TuristaAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {


    Scaffold(
        bottomBar = {
            BottomBarScreen()
        },
        floatingActionButton = {
            FloatingScreen()
        },
    ) {
        MainContent()
    }
}

@Composable
fun FloatingScreen() {
    FloatingActionButton(onClick = { }) {
        Icon(Icons.Default.Add, contentDescription = "Add")
    }
}

@Composable
fun BottomBarScreen() {
    BottomAppBar(

    ) {
        NavigationBarItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = {
                Icon(Icons.Default.Home, contentDescription = "Home")
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(painter = painterResource(id = R.drawable.car), contentDescription = "Home")
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(Icons.Default.Settings, contentDescription = "Home")
            }
        )
    }
}

@Composable
private fun MainContent() {
    Box(Modifier.fillMaxSize()) {
        GoogleMapScreen()

        Column(
            modifier = Modifier.align(Alignment.TopEnd).padding(8.dp)
        ) {
            Icon(
                painterResource(id = R.drawable.fullscreen), contentDescription = "Fullscreen",
                modifier = Modifier
                    .size(48.dp),
                tint = Color.Black
            )
            Icon(
                painterResource(id = R.drawable.tune), contentDescription = "Filter",
                modifier = Modifier
                    .size(48.dp),
                tint = Color.Black
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPrev() {
    TuristaAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen()
        }
    }

}