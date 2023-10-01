package com.example.turistaapp.main.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun FloatingActionButtonScreen(onClickButton: () -> Unit) {
    FloatingActionButton(onClick = { onClickButton() }) {
        Icon(Icons.Default.Add, contentDescription = "Add")
    }
}