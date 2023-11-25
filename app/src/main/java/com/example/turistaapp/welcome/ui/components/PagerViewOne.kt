package com.example.turistaapp.welcome.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.turistaapp.R
import com.example.turistaapp.home.ui.components.LottiePreview

@Composable
fun PagerViewOne(title: String, caption: String) {
    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        LottiePreview("", R.raw.loading_logo)
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
        )
        Text(
            text = caption,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}
