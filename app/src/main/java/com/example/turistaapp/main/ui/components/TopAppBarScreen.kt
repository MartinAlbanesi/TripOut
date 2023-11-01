package com.example.turistaapp.main.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarScreen(
    title: String,
    isMarkerSelected : Boolean,
    color : Color = LocalContentColor.current,
    onClickNavigationBack: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = title)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent.copy(alpha = 0.3f),
            titleContentColor = color
        ),
        navigationIcon = {
            if(isMarkerSelected){
                IconButton(onClick = { onClickNavigationBack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = Icons.Default.ArrowBack.name,
                        tint = color
                    )
                }
            }
        },
    )
}
