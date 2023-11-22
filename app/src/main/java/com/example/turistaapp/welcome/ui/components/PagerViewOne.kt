package com.example.turistaapp.welcome.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.turistaapp.R
import com.example.turistaapp.home.ui.components.LottiePreview

@Composable
fun PagerViewOne(text: String) {
    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        LottiePreview("", R.raw.trip_out_logo)
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
    }
}
