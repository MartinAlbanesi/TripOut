package com.example.turistaapp.main.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarScreen(
    title: String,
    iconsNavigation: ImageVector? = Icons.Outlined.ArrowBack,
    iconsAction: List<ImageVector> = emptyList(),
    onClickNavigationBack: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if(iconsNavigation != null){
                IconButton(onClick = { onClickNavigationBack }) {
                    Icon(imageVector = iconsNavigation, contentDescription = null)
                }
            }
        },
        actions = {
            iconsAction.forEach { icon ->
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            }
        },
    )
}
