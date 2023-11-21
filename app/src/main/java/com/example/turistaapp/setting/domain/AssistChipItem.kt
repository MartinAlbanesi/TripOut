package com.example.turistaapp.setting.domain

import androidx.compose.ui.graphics.vector.ImageVector

data class AssistChipItem(
    val icon: ImageVector,
    val text: String,
    val onClick: () -> Unit,
)
