package com.example.turistaapp.setting.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextWithComposable(
    title: String,
    errorMessage: String,
    isShowComposable: Boolean,
    composable: @Composable () -> Unit,
) {
//    Column {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier
            .padding(bottom = 8.dp),
    )
    if (isShowComposable) {
        composable()
    } else {
        PlaceholderElevatedCard(
            text = errorMessage,
            modifier = Modifier,
        )
    }
//    }
}
