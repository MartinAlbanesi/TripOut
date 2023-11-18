package com.example.turistaapp.setting.ui

import androidx.compose.ui.graphics.vector.ImageVector

data class AssistChipItem(
    val icon: ImageVector,
    val text: String,
    val onClick: () -> Unit,
)
